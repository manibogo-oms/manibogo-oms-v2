package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.CommonResponse;
import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.order.command.application.EditOrderCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.EditOrderService;
import kr.tatine.manibogo_oms_v2.order.command.application.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.FulfillmentDao;
import kr.tatine.manibogo_oms_v2.product.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/v2/fulfillment")
@RequiredArgsConstructor
public class FulfillmentController {

    private final FulfillmentDao fulfillmentDao;

    private final ProductDao productDao;

    private final RegionDao regionDao;

    private final EditOrderService editOrderService;

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
    public String fulfillment(
            @PageableDefault Pageable pageable,
            Model model,
            @ModelAttribute FulfillmentQueryParams queryParams,
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

        final Page<FulfillmentDto> page = fulfillmentDao.findAll(pageable, queryParams);
        final List<FulfillmentDto> fulfillmentList = page.getContent();

        model.addAttribute("fulfillmentList", fulfillmentList);

        model.addAttribute("rowsForm", initEditForm(fulfillmentList));

        model.addAttribute("nextSortParams", getNextSortParams(pageable.getSort()));

        model.addAttribute("page", page);

        return "fulfillment";
    }


    @PostMapping("/edit")
    public String editSummaries(
            @ModelAttribute("rowsForm") FulfillmentForm rowsForm,
            RedirectAttributes redirectAttributes) {

        log.debug("[ItemOrderController.editSummaries] rows = {}", rowsForm);

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
               editOrderService.edit(new EditOrderCommand(
                       row.getOrderNumber(), row.getState(), row.getDispatchDeadline(), row.getPreferredShippingDate(), row.getShippingMethod(), row.getChargeType(), row.getTrackingNumber(), row.getParcelCompany(), row.getPurchaseMemo(), row.getShippingMemo(), row.getAdminMemo()));

            } catch (AlreadyDispatchedException alreadyDispatchedException) {
                errorResult.rejectValue(getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException alreadyShippedException) {
                errorResult.rejectValue(getRowsField(i, "preferredShippingDate"), "alreadyShipped.editItemOrder.preferredShipsOn");

            } catch (OrderNotFoundException ex) {
                errorResult.reject("notFound.fulfillment", new Object[]{ row.getId() });
            }
        });

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.editSummaries", errorResult));

        return redirectWithQueryParams("/v2/fulfillment");
    }


    private String redirectWithQueryParams(String redirectPath) {
        final String queryString = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getQueryString();

        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(redirectPath);

        if (queryString != null && !queryString.isEmpty()) {
            uriBuilder.query(queryString);
        }

        return "redirect:" + uriBuilder.toUriString();
    }


    private String getRowsField(int index, String fieldName) {
        return "%s[%d].%s".formatted("rows", index, fieldName);
    }


    private FulfillmentForm initEditForm(List<FulfillmentDto> fulfillmentList) {

        final FulfillmentForm editForm = new FulfillmentForm();

        editForm.setRows(fulfillmentList.stream().map(FulfillmentDto::toEditFormRow).toList());

        return editForm;
    }

    private Map<String, String> getNextSortParams(Sort currentSort) {

        Map<String , String> nextSortParams = new HashMap<>();

        for (FulfillmentSortParam sortField : FulfillmentSortParam.values()) {

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
