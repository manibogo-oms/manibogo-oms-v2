package kr.tatine.manibogo_oms_v2.shipping.ui;

import kr.tatine.manibogo_oms_v2.common.model.ShippingNumber;
import kr.tatine.manibogo_oms_v2.common.ui.PageView;
import kr.tatine.manibogo_oms_v2.shipping.command.domain.ShippingNotFoundException;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingView;
import kr.tatine.manibogo_oms_v2.shipping.query.port.in.QueryShippingOrderUseCase;
import kr.tatine.manibogo_oms_v2.shipping.query.port.in.QueryShippingUseCase;
import kr.tatine.manibogo_oms_v2.shipping.ui.dto.in.ShippingQueryForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/v2/shipping")
@RequiredArgsConstructor
public class ShippingPageController {

    private final QueryShippingUseCase queryShippingUseCase;

    private final QueryShippingOrderUseCase queryShippingOrderUseCase;

    @GetMapping
    public String shipping(
            Model model,
            @ModelAttribute(name = "query") ShippingQueryForm query,
            @PageableDefault Pageable pageable
    ) {
        final Page<ShippingView> page =
                queryShippingUseCase.findAll(query.toQuery(), pageable);

        model.addAttribute("page", PageView.of(page));
        return "shipping";
    }

    @GetMapping("/{shippingNumber}/invoice")
    public String shippingInvoice(
            Model model,
            @PathVariable String shippingNumber
    ) {
        final ShippingNumber number = new ShippingNumber(shippingNumber);

        model.addAttribute("shippingOrders",
                queryShippingOrderUseCase.findAllByShippingNumber(number));

        model.addAttribute("shipping",
                queryShippingUseCase.findById(number)
                        .orElseThrow(ShippingNotFoundException::new));

        return "shipping_invoice";
    }


}
