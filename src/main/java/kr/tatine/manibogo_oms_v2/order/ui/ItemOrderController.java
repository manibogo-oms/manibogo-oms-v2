package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.common.model.CommonResponse;
import kr.tatine.manibogo_oms_v2.common.model.ErrorLevel;
import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.order.command.application.EditItemOrderSummaryService;
import kr.tatine.manibogo_oms_v2.order.command.application.ItemOrderNotFoundException;
import kr.tatine.manibogo_oms_v2.order.command.application.ProceedItemOrderStateService;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.CannotProceedToTargetStateException;
import kr.tatine.manibogo_oms_v2.order.command.domain.exception.StateAlreadyProceededException;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ItemOrderState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Controller
@RequestMapping("/v2/item-orders")
@RequiredArgsConstructor
public class ItemOrderController {

    private final EditItemOrderSummaryService editItemOrderSummaryService;

    private final ProceedItemOrderStateService proceedItemOrderStateService;

    @PostMapping("/edit-summaries")
    public String editSummaries(
            @ModelAttribute("rowsForm") ItemOrderRowsForm rowsForm,
            RedirectAttributes redirectAttributes) {

        log.debug("[ItemOrderController.editSummaries] rows = {}", rowsForm);

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                editItemOrderSummaryService.edit(row.toEditSummaryCommand());

            } catch (AlreadyDispatchedException alreadyDispatchedException) {
                errorResult.rejectValue(getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException alreadyShippedException) {
                errorResult.rejectValue(getRowsField(i, "preferredShipsOn"), "alreadyShipped.editItemOrder.preferredShipsOn");

            } catch (ItemOrderNotFoundException ex) {
                errorResult.reject("notFound.itemOrder", new Object[]{ row.getItemOrderNumber() });
            }
        });

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.editSummaries", errorResult));

        return redirectWithQueryParams("/v2/fulfillment");
    }

    @PostMapping("/proceed-state/purchased")
    public String proceedToPurchased(
            @ModelAttribute("rowsForm") ItemOrderRowsForm rowsForm,
            RedirectAttributes redirectAttributes) {
        return proceedState(ItemOrderState.PURCHASED, rowsForm, redirectAttributes);
    }

    @PostMapping("/proceed-state/dispatched")
    public String proceedToDispatched(
            @ModelAttribute("rowsForm") ItemOrderRowsForm rowsForm,
            RedirectAttributes redirectAttributes) {
        return proceedState(ItemOrderState.DISPATCHED, rowsForm, redirectAttributes);
    }

    @PostMapping("/proceed-state/shipped")
    public String proceedToShipped(
            @ModelAttribute("rowsForm") ItemOrderRowsForm rowsForm,
            RedirectAttributes redirectAttributes) {
        return proceedState(ItemOrderState.SHIPPED, rowsForm, redirectAttributes);
    }


    private String proceedState(ItemOrderState targetState, ItemOrderRowsForm rowsForm, RedirectAttributes redirectAttributes) {
        log.debug("[ItemOrderController.proceedState] rows = {}", rowsForm);
        log.debug("[ItemOrderController.proceedState] targetState = {}", targetState);

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                proceedItemOrderStateService.proceed(row.toProceedStateCommand(targetState));

            } catch (StateAlreadyProceededException ex) {
                errorResult.rejectValue(
                        getRowsField(i, "itemOrderState"),
                        ErrorLevel.WARN,
                        "stateAlreadyProceed",
                        new Object[] { targetState.getDescription() });
            } catch (CannotProceedToTargetStateException ex) {
                errorResult.rejectValue(
                        getRowsField(i, "itemOrderState"),
                        "cannotProceedState",
                        new Object[] { targetState.getDescription() });

            } catch (ItemOrderNotFoundException ex) {
                errorResult.reject("notFound.itemOrder", new Object[]{ row.getItemOrderNumber() });
            }
        });

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.proceedState", new Object[]{targetState.getDescription()}, errorResult));

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



}
