package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.order.command.domain.OrderState;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderRowDto;
import kr.tatine.manibogo_oms_v2.order.query.dto.OrderRowsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/v2/orders")
public class OrderController {

    @GetMapping
    public String orders(Model model) {

        OrderRowsDto orderRowsDto = new OrderRowsDto();

        OrderRowDto rowDto = new OrderRowDto();

        rowDto.setRowSelected(true);
        rowDto.setOrderState(OrderState.SHIPPED);
        rowDto.setOrderNumber("2025051637129311");
        rowDto.setProductName("베아투스 매트리스 [사이즈: 1500X2000]");
        rowDto.setAmount(1);
        rowDto.setShippingBundleCount(1);
        rowDto.setShippingRegionName("경기");
        rowDto.setBuyerName("홍길동");
        rowDto.setReceiverName("홍길동");
        rowDto.setOrderPlacedAt(LocalDateTime.parse("2025-05-18T11:55:01"));
        rowDto.setDispatchDeadlineAt(LocalDateTime.parse("2025-06-09T11:55:01"));
        rowDto.setPurchasedAt(LocalDateTime.parse("2025-05-19T11:57:01"));
        rowDto.setDispatchedAt(LocalDateTime.parse("2025-05-19T11:57:01"));
        rowDto.setShippedAt(LocalDateTime.parse("2025-05-20T11:57:01"));
        rowDto.setPurchaseMemo("최대한 빨리 제작요청");

        orderRowsDto.setRows(List.of(rowDto));

        model.addAttribute("orderRows", orderRowsDto);

        return "orders";
    }

}
