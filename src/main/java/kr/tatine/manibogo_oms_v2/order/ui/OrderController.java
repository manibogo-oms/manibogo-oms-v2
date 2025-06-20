package kr.tatine.manibogo_oms_v2.order.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class OrderController {


    @GetMapping("/add")
    public String getAddOrder() {
        return "addOrder";
    }

}
