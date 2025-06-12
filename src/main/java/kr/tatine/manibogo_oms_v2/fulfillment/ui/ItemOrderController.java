package kr.tatine.manibogo_oms_v2.fulfillment.ui;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult
    ) {

        log.debug("[ItemOrderController.editSummaries] rowsForm = {}", rowsForm);

        int selectedRowCount = 0;

        for (int i = 0; i < rowsForm.getRows().size(); i ++) {
            final ItemOrderRowsForm.Row row = rowsForm.getRows().get(i);

            if (!row.getIsSelected()) continue;

            selectedRowCount ++;

            try {
                editItemOrderSummaryService.edit(row.toEditSummaryCommand());
            } catch (AlreadyDispatchedException alreadyDispatchedException) {

                bindingResult.rejectValue(
                        getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException alreadyShippedException) {

                bindingResult.rejectValue(
                        getRowsField(i, "preferredShipsOn"), "alreadyShipped.editItemOrder.preferredShipsOn");
            } catch (ItemOrderNotFoundException notFoundException) {
                bindingResult.reject("notFound.itemOrder", new Object[]{ row.getItemOrderNumber() }, "notFound");
            }

        }

        if (selectedRowCount == 0) {
            bindingResult.reject("requireSelect");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rowsForm", bindingResult);
            redirectAttributes.addFlashAttribute("rowsForm", rowsForm);
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "상품 주문이 성공적으로 수정되었습니다.");
        }

        return "redirect:/v2/fulfillment";
    }

    @PostMapping("/proceed-state")
    public String proceedState(
            @RequestParam("targetState") ItemOrderState targetState,
            @ModelAttribute("rowsForm") ItemOrderRowsForm rowsForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        log.debug("[ItemOrderController.proceedState] rowsForm = {}", rowsForm);
        log.debug("[ItemOrderController.proceedState] targetState = {}", targetState);


        int selectedRowCount = 0;

        for (int i = 0; i < rowsForm.getRows().size(); i ++) {
            final ItemOrderRowsForm.Row row = rowsForm.getRows().get(i);

            if (!row.getIsSelected()) continue;

            selectedRowCount ++;

            try {
                proceedItemOrderStateService.proceed(row.toProceedStateCommand(targetState));

            } catch (AlreadyDispatchedException ex) {
                bindingResult.rejectValue(
                        getRowsField(i, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");
            } catch (AlreadyShippedException ex) {
                bindingResult.rejectValue(
                        getRowsField(i, "preferredShipsOn"), "alreadyShipped.editItemOrder.preferredShipsOn");
            } catch (ItemOrderNotFoundException ex) {
                bindingResult.reject("notFound.itemOrder", new Object[]{ row.getItemOrderNumber() }, "notFound");
            } catch (StateAlreadyProceededException ex) {
                bindingResult.rejectValue(
                        getRowsField(i, "itemOrderState"),
                        "stateAlreadyProceed",
                        new Object[] { targetState.getDescription() },
                        "stateAlreadyProceed");
            } catch (CannotProceedToTargetStateException ex) {

                bindingResult.rejectValue(
                        getRowsField(i, "itemOrderState"),
                        "cannotProceedState",
                        new Object[] { targetState.getDescription() },
                        "cannotProceedState"
                );

            }

        }

        if (selectedRowCount == 0) {
            bindingResult.reject("requireSelect");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.rowsForm", bindingResult);
            redirectAttributes.addFlashAttribute("rowsForm", rowsForm);
        } else {
            redirectAttributes.addFlashAttribute("successMessage", "상품 주문의 상태가 성공적으로 진전되었습니다.");
        }


        return "redirect:/v2/fulfillment";
    }


    private String getRowsField(int index, String fieldName) {
        return "%s[%d].%s".formatted("rows", index, fieldName);
    }

}
