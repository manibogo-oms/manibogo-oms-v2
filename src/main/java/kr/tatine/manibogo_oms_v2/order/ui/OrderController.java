package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.OrderState;
import kr.tatine.manibogo_oms_v2.common.ui.CommonResponse;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.SalesChannel;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderDto;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.DateSearchParam;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.DetailSearchParam;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.OrderQueryParams;
import kr.tatine.manibogo_oms_v2.order.query.dto.in.OrderSortParam;
import kr.tatine.manibogo_oms_v2.order.query.port.in.OrderQueryUseCase;
import kr.tatine.manibogo_oms_v2.product.query.ProductDao;
import kr.tatine.manibogo_oms_v2.product.query.ProductDto;
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

@Slf4j
@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderQueryUseCase orderQueryUseCase;

    private final ProductDao productDao;

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

        final Page<OrderDto> page = orderQueryUseCase.findAll(pageable, queryParams);
        final List<OrderDto> fulfillmentList = page.getContent();

        model.addAttribute("fulfillmentList", fulfillmentList);

        model.addAttribute("rowsForm", initEditForm(fulfillmentList));

        model.addAttribute("nextSortParams", getNextSortParams(pageable.getSort()));

        model.addAttribute("page", page);

        return "orders";
    }

    private EditOrderSummaryForm initEditForm(List<OrderDto> fulfillmentList) {

        final EditOrderSummaryForm editForm = new EditOrderSummaryForm();

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
