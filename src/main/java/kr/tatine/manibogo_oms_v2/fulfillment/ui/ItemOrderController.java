package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditItemOrderSummaryCommand;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditItemOrderSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/v2/item-orders")
@RequiredArgsConstructor
public class ItemOrderController {

    private final EditItemOrderSummaryService editItemOrderSummaryService;

    @PostMapping("/edit-summaries")
    public String editSummaries(
            @ModelAttribute EditItemOrderSummariesForm form) {

        log.debug("[ItemOrderController.editSummaries] form = {}", form);

        for (EditItemOrderSummariesForm.Row row : form.getRows()) {
            if (!row.getIsSelected()) continue;
            editItemOrderSummaryService.edit(row.toCommand());
        }

        return "redirect:/v2/fulfillment";
    }


}
