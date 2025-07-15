package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.model.CommonResponse;
import kr.tatine.manibogo_oms_v2.common.model.ErrorLevel;
import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.EditOrderSummaryCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.dto.ProceedOrderStateCommand;
import kr.tatine.manibogo_oms_v2.order.command.application.exception.OrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.application.service.EditOrderService;
import kr.tatine.manibogo_oms_v2.order.command.application.service.ProceedOrderStateService;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.CannotProceedToTargetStateException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.StateAlreadyProceededException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.OrderState;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.order.query.dao.OrderDao;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class EditOrderController {

    private final OrderDao orderDao;

    private final EditOrderService editOrderService;

    @ModelAttribute("orderStates")
    public OrderState[] orderStates() {
        return OrderState.values();
    }

    @ModelAttribute("shippingMethods")
    public ShippingMethod[] shippingMethods() {
        return ShippingMethod.values();
    }

    @ModelAttribute("shippingChargeTypes")
    public ChargeType[] shippingChargeTypes() {
        return ChargeType.values();
    }

    @GetMapping("/{orderNumber}/edit")
    @Transactional(readOnly = true)
    public String getEdit(@PathVariable String orderNumber, Model model) {
        final OrderDto orderDto = orderDao.findById(orderNumber)
                .orElseThrow(OrderNotFoundException::new);

        model.addAttribute("form", EditOrderForm.of(orderDto));

        return "editOrder";
    }

    @PostMapping("/{orderNumber}/edit")
    public String edit(
            @PathVariable String orderNumber,
            @ModelAttribute("form") EditOrderForm form,
            Model model
    ) {

        final ErrorResult errorResult = new ErrorResult();

        try {
            editOrderService.editDetail(form.toCommand());
        } catch (ValidationErrorException ex) {
            ex.getValidationErrors().forEach((err) -> errorResult.rejectValue(err.getName(), err.getErrorCode()));
        } catch (OrderNotFoundException ex) {
            errorResult.reject("notFound.order", new Object[]{ form.getOrderNumber() });
        }

        if (errorResult.hasError()) {
            model.addAttribute("errors", errorResult);
            return "editOrder";
        }

        return "redirect:/v2/orders/{orderNumber}/edit";
    }

    @PostMapping("/editSummary")
    public String editSummary(
            @ModelAttribute("rowsForm") EditOrderSummaryForm rowsForm,
            RedirectAttributes redirectAttributes) {

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                editOrderService.editSummary(new EditOrderSummaryCommand(
                        row.getOrderNumber(), row.getState(), row.getDispatchDeadline(), row.getPreferredShippingDate(), row.getShippingMethod(), row.getChargeType(), row.getTrackingNumber(), row.getParcelCompany(), row.getPurchaseMemo(), row.getShippingMemo(), row.getAdminMemo()));

            } catch (AlreadyDispatchedException alreadyDispatchedException) {
                errorResult.rejectValue(getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException alreadyShippedException) {
                errorResult.rejectValue(getRowsField(i, "preferredShippingDate"), "alreadyShipped.editItemOrder.preferredShipsOn");

            } catch (OrderNotFoundException ex) {
                errorResult.reject("notFound.order", new Object[]{ row.getOrderNumber() });
            }
        });

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.editSummaries", errorResult));

        return redirectWithQueryParams("/v2/fulfillment");
    }

    private final ProceedOrderStateService proceedStateService;

    @PostMapping("/proceedState/purchased")
    public String proceedToPurchased(
            @ModelAttribute("rowsForm") EditOrderSummaryForm rowsForm,
            RedirectAttributes redirectAttributes) {
        return proceedState(OrderState.PURCHASED, rowsForm, redirectAttributes);
    }

    @PostMapping("/proceedState/dispatched")
    public String proceedToDispatched(
            @ModelAttribute("rowsForm") EditOrderSummaryForm rowsForm,
            RedirectAttributes redirectAttributes) {
        return proceedState(OrderState.DISPATCHED, rowsForm, redirectAttributes);
    }

    @PostMapping("/proceedState/shipped")
    public String proceedToShipped(
            @ModelAttribute("rowsForm") EditOrderSummaryForm rowsForm,
            RedirectAttributes redirectAttributes) {
        return proceedState(OrderState.SHIPPED, rowsForm, redirectAttributes);
    }

    private String proceedState(OrderState targetState, EditOrderSummaryForm rowsForm, RedirectAttributes redirectAttributes) {

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                proceedStateService.proceed(new ProceedOrderStateCommand(row.getOrderNumber(), targetState));

            } catch (StateAlreadyProceededException ex) {
                errorResult.rejectValue(
                        getRowsField(i, "orderState"),
                        ErrorLevel.WARN,
                        "stateAlreadyProceed",
                        new Object[] { targetState.getDescription() });
            } catch (CannotProceedToTargetStateException ex) {
                errorResult.rejectValue(
                        getRowsField(i, "orderState"),
                        "cannotProceedState",
                        new Object[] { targetState.getDescription() });

            } catch (OrderNotFoundException ex) {
                errorResult.reject("notFound.order", new Object[]{ row.getOrderNumber() });
            }
        });

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.proceedState", new Object[]{targetState.getDescription()}, errorResult));

        return redirectWithQueryParams("/v2/orders");
    }

    private String getRowsField(int index, String fieldName) {
        return "%s[%d].%s".formatted("rows", index, fieldName);
    }

    private String redirectWithQueryParams(String redirectPath) {
        final String queryString = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getQueryString();

        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(redirectPath);

        if (queryString != null && !queryString.isEmpty()) {
            uriBuilder.query(queryString);
        }

        return "redirect:" + uriBuilder.toUriString();
    }



}
