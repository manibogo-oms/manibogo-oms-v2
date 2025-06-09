package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.ItemOrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.model.vo.SalesChannel;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentDto;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.FulfillmentListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/v2/fulfillment")
public class FulfillmentController {

    @ModelAttribute("itemOrderStates")
    public ItemOrderState[] orderStates() {
        return ItemOrderState.values();
    }

    @ModelAttribute("salesChannels")
    public SalesChannel[] salesChannels() {
        return SalesChannel.values();
    }

    @GetMapping
    public String fulfillment(
            Model model,
            @ModelAttribute SynchronizeResponse synchronizeResponse) {

        model.addAttribute("synchronizeResponse", synchronizeResponse);

        FulfillmentListDto fulfillmentListDto = new FulfillmentListDto();

        model.addAttribute("fulfillmentList", fulfillmentListDto);

        return "fulfillment";
    }

    @PostMapping("/edit")
    public String editOrders(@ModelAttribute("orders") EditOrderListForm editOrderListForm) {
        for (EditOrderForm order : editOrderListForm.getOrders()) {
            log.info("order = {}", order);
        }

        return "redirect:/v2/fulfillment";
    }

}
