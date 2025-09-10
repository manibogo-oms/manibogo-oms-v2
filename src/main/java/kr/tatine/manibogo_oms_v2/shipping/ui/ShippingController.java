package kr.tatine.manibogo_oms_v2.shipping.ui;

import kr.tatine.manibogo_oms_v2.shipping.query.ShippingPageView;
import kr.tatine.manibogo_oms_v2.shipping.query.ShippingQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/v2/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingQueryService queryService;


    @GetMapping
    @ResponseBody
    public void page(@PageableDefault Pageable pageable) {
        Page<ShippingPageView> all = queryService.findAll(pageable);

        log.debug(all.toString());
    }

}
