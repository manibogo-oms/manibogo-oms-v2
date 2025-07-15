package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.CommonResponse;
import kr.tatine.manibogo_oms_v2.order.query.dao.OrderDao;
import kr.tatine.manibogo_oms_v2.order.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.query.dto.*;
import kr.tatine.manibogo_oms_v2.product.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.product.query.dto.ProductDto;
import kr.tatine.manibogo_oms_v2.synchronize.ui.SynchronizeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderDao orderDao;

    private final ProductDao productDao;

    private final RegionDao regionDao;

    @ModelAttribute("itemOrderStates")
    public OrderState[] orderStates() {
        return OrderState.values();
    }

    @ModelAttribute("salesChannels")
    public SalesChannel[] salesChannels() {
        return SalesChannel.values();
    }

    @ModelAttribute("detailSearchParams")
    public DetailSearchParam[] detailSearchParams() {
        return DetailSearchParam.values();
    }

    @ModelAttribute("dateSearchParams")
    public DateSearchParam[] dateSearchParams() {
        return DateSearchParam.values();
    }

    @ModelAttribute("products")
    public List<ProductDto> products() {
        return productDao.findEnabled();
    }

    @ModelAttribute("regions")
    public Map<String, List<String>> regions() {
        return regionDao.findDistinctAll().stream()
                .collect(Collectors.groupingBy(RegionDto::getSido, Collectors.mapping(RegionDto::getSigungu, Collectors.toList())));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String orders(
            @PageableDefault Pageable pageable,
            Model model,
            @ModelAttribute OrderQueryParams queryParams,
            @ModelAttribute SynchronizeResponse synchronizeResponse,
            @ModelAttribute("response") CommonResponse response) {

        model.addAttribute("queryParams", queryParams);

        if (queryParams.getProductNumber() != null && !queryParams.getProductNumber().isBlank()) {
            final Optional<ProductDto> productOptional = products().stream()
                    .filter(e -> e.getNumber().equals(queryParams.getProductNumber()))
                    .findFirst();

            productOptional.ifPresent(p ->
                    model.addAttribute("queryProductName", p.getName()));
        }

        model.addAttribute("synchronizeResponse", synchronizeResponse);
        model.addAttribute("response", response);

        final Page<OrderDto> page = orderDao.findAll(pageable, queryParams);
        final List<OrderDto> fulfillmentList = page.getContent();

        model.addAttribute("fulfillmentList", fulfillmentList);

        model.addAttribute("rowsForm", initEditForm(fulfillmentList));

        model.addAttribute("nextSortParams", getNextSortParams(pageable.getSort()));

        model.addAttribute("page", page);

        return "orders";
    }

    private EditOrderForm initEditForm(List<OrderDto> fulfillmentList) {

        final EditOrderForm editForm = new EditOrderForm();

        editForm.setRows(fulfillmentList.stream().map(OrderDto::toEditFormRow).toList());

        return editForm;
    }

    private Map<String, String> getNextSortParams(Sort currentSort) {

        Map<String , String> nextSortParams = new HashMap<>();

        for (OrderSortParam sortField : OrderSortParam.values()) {

            final Optional<Sort.Order> optionalOrder = currentSort.stream()
                    .filter(order -> order.getProperty().equals(sortField.name()))
                    .findFirst();

            // 현재 정렬 없음 -> 다음은 내림차순 (DESC)
            if (optionalOrder.isEmpty()) {
                nextSortParams.put(sortField.name(), sortField.name() + ",DESC");
                continue;
            }

            final Sort.Order order = optionalOrder.get();

            // 현재 오름차순(ASC) -> 정렬 해제(UNSORTED)
            if (order.getDirection() == Sort.Direction.ASC) {
                nextSortParams.put(sortField.name(), "UNSORTED");
                continue;
            }

            // 현재 내림차순(DESC) -> 다음은 오름차순(ASC)
            nextSortParams.put(sortField.name(), sortField.name() + ",ASC");
        }

        return nextSortParams;
    }

}
