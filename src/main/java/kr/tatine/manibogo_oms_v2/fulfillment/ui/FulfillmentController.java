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

        fulfillmentListDto.setFulfillmentList(List.of(
                createRowDto(ItemOrderState.PLACED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.PURCHASED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.DISPATCHED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.SHIPPED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.CONFIRMED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.CANCELLED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.REFUNDED, SalesChannel.SMART_STORE),
                createRowDto(ItemOrderState.PLACED, SalesChannel.LOCAL)
        ));

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

    private FulfillmentDto createRowDto(ItemOrderState orderState, SalesChannel salesChannel) {
        FulfillmentDto fulfillmentDto = new FulfillmentDto();

        fulfillmentDto.setRowSelected(false);
        fulfillmentDto.setSalesChannel(salesChannel);
        fulfillmentDto.setOrderState(orderState);
        fulfillmentDto.setOrderNumber("2025051637129311");
        fulfillmentDto.setProductName("베아투스 매트리스 [사이즈: 1500X2000]");
        fulfillmentDto.setAmount(2);
        fulfillmentDto.setShippingBundleCount(2);
        fulfillmentDto.setShippingRegionName("경기");
        fulfillmentDto.setBuyerName("홍길동");
        fulfillmentDto.setReceiverName("홍길동");
        fulfillmentDto.setOrderPlacedOn(LocalDate.parse("2025-05-18"));
        fulfillmentDto.setDispatchDeadlineOn(LocalDate.parse("2025-06-09"));
        fulfillmentDto.setPurchasedOn(LocalDate.parse("2025-05-19"));
        fulfillmentDto.setDispatchedOn(LocalDate.parse("2025-05-19"));
        fulfillmentDto.setShippedOn(LocalDate.parse("2025-05-20"));
        fulfillmentDto.setPurchaseMemo("최대한 빨리 제작요청");
        return fulfillmentDto;
    }

}
