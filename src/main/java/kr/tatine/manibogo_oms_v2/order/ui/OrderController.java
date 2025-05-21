package kr.tatine.manibogo_oms_v2.order.ui;

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

    @GetMapping
    public String orders(Model model) {

        OrderRowsDto orderRowsDto = new OrderRowsDto();

        OrderRowDto rowDto = new OrderRowDto();

        rowDto.setRowSelected(true);
        rowDto.setOrderState(OrderState.SHIPPED);
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

        orderRowsDto.setRows(List.of(rowDto));

        model.addAttribute("orderRows", orderRowsDto);

        return "orders";
    }

}
