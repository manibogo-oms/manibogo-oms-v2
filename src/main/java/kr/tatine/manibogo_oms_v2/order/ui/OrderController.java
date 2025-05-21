package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.order.command.domain.OrderLocation;
import kr.tatine.manibogo_oms_v2.order.command.domain.OrderState;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderRowDto;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderRowsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

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

        OrderRowsDto orderRowsDto = new OrderRowsDto();

        orderRowsDto.setRows(List.of(
                createRowDto(OrderState.PLACED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.PURCHASED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.DISPATCHED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.SHIPPED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.CONFIRMED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.CANCELED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.REFUNDED, OrderLocation.SMARTSTORE),
                createRowDto(OrderState.PLACED, OrderLocation.LOCAL)
        ));

        model.addAttribute("orderRows", orderRowsDto);

        return "orders";
    }

    private OrderRowDto createRowDto(OrderState orderState, OrderLocation orderLocation) {
        OrderRowDto rowDto = new OrderRowDto();

        rowDto.setRowSelected(false);
        rowDto.setOrderLocation(orderLocation);
        rowDto.setOrderState(orderState);
        rowDto.setOrderNumber("2025051637129311");
        rowDto.setProductName("베아투스 매트리스 [사이즈: 1500X2000]");
        rowDto.setAmount(2);
        rowDto.setShippingBundleCount(2);
        rowDto.setShippingRegionName("경기");
        rowDto.setBuyerName("홍길동");
        rowDto.setReceiverName("홍길동");
        rowDto.setOrderPlacedOn(LocalDate.parse("2025-05-18"));
        rowDto.setDispatchDeadlineOn(LocalDate.parse("2025-06-09"));
        rowDto.setPurchasedOn(LocalDate.parse("2025-05-19"));
        rowDto.setDispatchedOn(LocalDate.parse("2025-05-19"));
        rowDto.setShippedOn(LocalDate.parse("2025-05-20"));
        rowDto.setPurchaseMemo("최대한 빨리 제작요청");
        return rowDto;
    }

}
