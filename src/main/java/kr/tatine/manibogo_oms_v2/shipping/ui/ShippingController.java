package kr.tatine.manibogo_oms_v2.shipping.ui;

import kr.tatine.manibogo_oms_v2.common.ui.PageView;
import kr.tatine.manibogo_oms_v2.shipping.query.dto.out.ShippingPageView;
import kr.tatine.manibogo_oms_v2.shipping.query.ShippingQueryService;
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
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/v2/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingQueryService queryService;

    @GetMapping
    public String shipping(
            Model model,
            @ModelAttribute(name = "query") ShippingQueryForm query,
            @PageableDefault Pageable pageable
    ) {
        final Page<ShippingPageView> page = queryService.findAll(query.toQuery(), pageable);

        model.addAttribute("page", PageView.of(page));
        return "shippings";
    }

}
