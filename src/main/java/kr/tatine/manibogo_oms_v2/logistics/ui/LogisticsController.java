package kr.tatine.manibogo_oms_v2.logistics.ui;

import kr.tatine.manibogo_oms_v2.logistics.query.dao.LogisticsDao;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsDto;
import kr.tatine.manibogo_oms_v2.logistics.query.dto.LogisticsQueryParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v2/logistics")
@RequiredArgsConstructor
public class LogisticsController {

    private final LogisticsDao logisticsDao;

    @GetMapping
    @Transactional(readOnly = true)
    public String logistics(@PageableDefault Pageable pageable, Model model) {

        final Page<LogisticsDto> page =
                logisticsDao.findAll(pageable, new LogisticsQueryParams());

        model.addAttribute("logisticsList", page.getContent());
        model.addAttribute("page", page);

        return "logistics";
    }

}
