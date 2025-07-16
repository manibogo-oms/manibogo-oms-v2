package kr.tatine.manibogo_oms_v2.logistics.ui;

import kr.tatine.manibogo_oms_v2.logistics.query.dao.LogisticsDao;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsDto;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsQueryParams;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.DetailSearchParam;
import kr.tatine.manibogo_oms_v2.order.query.dao.OrderDao;
import kr.tatine.manibogo_oms_v2.order.query.dao.RegionDao;
import kr.tatine.manibogo_oms_v2.order.query.dto.RegionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/v2/logistics")
@RequiredArgsConstructor
public class LogisticsController {

    private final LogisticsDao logisticsDao;

    private final RegionDao regionDao;

    private final OrderDao orderDao;

    @ModelAttribute("detailSearchParams")
    public DetailSearchParam[] searchParams() {
        return DetailSearchParam.values();
    }

    @ModelAttribute("regions")
    public Map<String, List<String>> regions() {
        return regionDao.findDistinctAll().stream()
                .collect(Collectors.groupingBy(RegionDto::getSido, Collectors.mapping(RegionDto::getSigungu, Collectors.toList())));
    }

    @GetMapping
    @Transactional(readOnly = true)
    public String logistics(@PageableDefault Pageable pageable,
                            @ModelAttribute("queryParams") LogisticsQueryParams queryParams,
                            Model model) {

        final Page<LogisticsDto> page = logisticsDao.findAll(pageable, queryParams);

        model.addAttribute("logisticsList", page.getContent());
        model.addAttribute("page", page);

        return "logistics";
    }

    @GetMapping("/{shippingBundleNumber}/invoice")
    public String invoice(@PathVariable String shippingBundleNumber, Model model) {

        model.addAttribute("orders",
                orderDao.findByShippingBundleNumber(shippingBundleNumber));

        return "logisticsInvoice";
    }

}
