package kr.tatine.manibogo_oms_v2.region.ui;

import kr.tatine.manibogo_oms_v2.region.query.dao.RegionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v2/regions")
public class RegionFragmentController {

    private final RegionDao regionDao;

    @GetMapping("/options")
    public String options(@RequestParam int level,
                          @RequestParam(required = false) String parent,
                          @RequestParam(required = false) String selected,
                          Model model) {
        model.addAttribute("regions", regionDao.findAll(level, parent));
        model.addAttribute("selected", selected);

        return "fragments/region :: options";
    }

}
