package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.order.OrderLocation;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.OrderState;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.OrderDto;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.OrderListDto;
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
@RequestMapping("/v2/orders")
public class OrderController {

    @ModelAttribute("orderStates")
    public OrderState[] orderStates() {
        return OrderState.values();
    }

    @ModelAttribute("orderLocations")
    public OrderLocation[] orderLocations() {
        return OrderLocation.values();
    }

    @GetMapping
    public String orders(Model model) {

        OrderListDto orderListDto = new OrderListDto();

        orderListDto.setOrders(List.of(
                createRowDto(OrderState.PLACED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.PURCHASED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.DISPATCHED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.SHIPPED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.CONFIRMED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.CANCELED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.REFUNDED, OrderLocation.SMART_STORE),
                createRowDto(OrderState.PLACED, OrderLocation.LOCAL)
        ));

        model.addAttribute("orderList", orderListDto);

        return "orders";
    }

    @PostMapping("/edit")
    public String editOrders(@ModelAttribute("orders") EditOrderListForm editOrderListForm) {
        for (EditOrderForm order : editOrderListForm.getOrders()) {
            log.info("order = {}", order);
        }

        return "redirect:/v2/orders";
    }

    private OrderDto createRowDto(OrderState orderState, OrderLocation orderLocation) {
        OrderDto orderDto = new OrderDto();

        orderDto.setRowSelected(false);
        orderDto.setOrderLocation(orderLocation);
        orderDto.setOrderState(orderState);
        orderDto.setOrderNumber("2025051637129311");
        orderDto.setProductName("베아투스 매트리스 [사이즈: 1500X2000]");
        orderDto.setAmount(2);
        orderDto.setShippingBundleCount(2);
        orderDto.setShippingRegionName("경기");
        orderDto.setBuyerName("홍길동");
        orderDto.setReceiverName("홍길동");
        orderDto.setOrderPlacedOn(LocalDate.parse("2025-05-18"));
        orderDto.setDispatchDeadlineOn(LocalDate.parse("2025-06-09"));
        orderDto.setPurchasedOn(LocalDate.parse("2025-05-19"));
        orderDto.setDispatchedOn(LocalDate.parse("2025-05-19"));
        orderDto.setShippedOn(LocalDate.parse("2025-05-20"));
        orderDto.setPurchaseMemo("최대한 빨리 제작요청");
        return orderDto;
    }

}
