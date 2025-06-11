package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditItemOrderSummaryService;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.AlreadyDispatchedException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.exception.AlreadyShippedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
@RequestMapping("/v2/item-orders")
@RequiredArgsConstructor
public class ItemOrderController {

    private final EditItemOrderSummaryService editItemOrderSummaryService;

    @PostMapping("/edit-summaries")
    public String editSummaries(
            @ModelAttribute("editForm") EditItemOrderSummariesForm editForm,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult
    ) {

        log.debug("[ItemOrderController.editSummaries] editForm = {}", editForm);

        final AtomicInteger indexHolder = new AtomicInteger();

        final AtomicInteger selectedRowCount = new AtomicInteger();

        editForm.getRows().forEach(row -> {
            final int index = indexHolder.getAndIncrement();

            if (!row.getIsSelected()) return;
            selectedRowCount.incrementAndGet();

            try {
                editItemOrderSummaryService.edit(row.toCommand());
            } catch (AlreadyDispatchedException alreadyDispatchedException) {

                bindingResult.rejectValue(
                        getIndexedFieldName(index, "dispatchDeadline"), "alreadyDispatched.editItemOrder.dispatchDeadline");

            } catch (AlreadyShippedException alreadyShippedException) {

                bindingResult.rejectValue(
                        getIndexedFieldName(index, "preferredShipsOn"), "alreadyShipped.editItemOrder.preferredShipsOn");
            }

        });

        if (selectedRowCount.get() == 0) {
            bindingResult.reject("requireSelect");
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editForm", bindingResult);
            redirectAttributes.addFlashAttribute("editForm", editForm);
        }

        return "redirect:/v2/fulfillment";
    }


    private String getIndexedFieldName(int index, String fieldName) {

        return "%s[%d].%s".formatted("rows", index, fieldName);
    }

}
