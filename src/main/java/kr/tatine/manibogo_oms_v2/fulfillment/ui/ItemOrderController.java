package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.common.model.CommonResponse;
import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditItemOrderSummaryService;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ItemOrderNotFoundException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ProceedItemOrderStateService;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.AlreadyShippedException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.CannotProceedToTargetStateException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.StateAlreadyProceededException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            RedirectAttributes redirectAttributes
    ) {

        log.debug("[ItemOrderController.editSummaries] rowsForm = {}", rowsForm);

        final ErrorResult errorResult = new ErrorResult();

        int selectedRowCount = 0;

        for (int i = 0; i < rowsForm.getRows().size(); i ++) {
            final ItemOrderRowsForm.Row row = rowsForm.getRows().get(i);

            if (!row.getIsSelected()) continue;

            selectedRowCount ++;

            final String itemOrderNumber = row.getItemOrderNumber();

            try {
                editItemOrderSummaryService.edit(row.toEditSummaryCommand());

            } catch (AlreadyDispatchedException alreadyDispatchedException) {
                errorResult.rejectValue(getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException alreadyShippedException) {
                errorResult.rejectValue(getRowsField(i, "preferredShipsOn"), "alreadyShipped.editItemOrder.preferredShipsOn");

            } catch (ItemOrderNotFoundException notFoundException) {
                errorResult.reject("notFound.itemOrder", new Object[]{itemOrderNumber});
            }
        }

        if (selectedRowCount == 0) {
            errorResult.reject("requireSelect");
        }

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.editSummaries", errorResult));

        return "redirect:/v2/fulfillment";
    }

    @PostMapping("/proceed-state")
    public String proceedState(
            @RequestParam("targetState") ItemOrderState targetState,
            @ModelAttribute("rowsForm") ItemOrderRowsForm rowsForm,
            RedirectAttributes redirectAttributes
    ) {

        log.debug("[ItemOrderController.proceedState] rowsForm = {}", rowsForm);
        log.debug("[ItemOrderController.proceedState] targetState = {}", targetState);

        final ErrorResult errorResult = new ErrorResult();

        int selectedRowCount = 0;

        for (int i = 0; i < rowsForm.getRows().size(); i ++) {
            final ItemOrderRowsForm.Row row = rowsForm.getRows().get(i);

            if (!row.getIsSelected()) continue;

            selectedRowCount ++;

            try {
                proceedItemOrderStateService.proceed(row.toProceedStateCommand(targetState));

            } catch (AlreadyDispatchedException ex) {
                errorResult.rejectValue(getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException ex) {
                errorResult.rejectValue(getRowsField(i, "preferredShipsOn"), "alreadyShipped.editItemOrder.preferredShipsOn");

            } catch (ItemOrderNotFoundException ex) {
                errorResult.reject("notFound.itemOrder", new Object[]{ row.getItemOrderNumber() });

            } catch (StateAlreadyProceededException ex) {
                errorResult.rejectValue(
                        getRowsField(i, "itemOrderState"),
                        "stateAlreadyProceed",
                        new Object[] { targetState.getDescription() });
            } catch (CannotProceedToTargetStateException ex) {

                errorResult.rejectValue(
                        getRowsField(i, "itemOrderState"),
                        "cannotProceedState",
                        new Object[] { targetState.getDescription() });

            }

        }

        if (selectedRowCount == 0) {
            errorResult.reject("requireSelect");
        }

        redirectAttributes.addFlashAttribute(
                "response",
                new CommonResponse("complete.proceedState", new Object[]{targetState.getDescription()}, errorResult));

        return "redirect:/v2/fulfillment";
    }


    private String getRowsField(int index, String fieldName) {
        return "%s[%d].%s".formatted("rows", index, fieldName);
    }


}
