package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.product.query.dao.VariantDao;
import kr.tatine.manibogo_oms_v2.product.query.dto.VariantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class OrderController {

    private final VariantDao variantDao;

    @ModelAttribute("productOptions")
    public Map<String, Map<String, List<VariantDto>>> productOptions() {
        return variantDao.findAll().stream().collect(
                groupingBy(VariantDto::getProductNumber,
                groupingBy(VariantDto::getKey, toList())));
    }

    @GetMapping("/add")
    public String getAddOrder(Model model) {

        if (!model.containsAttribute("form")) {
            AddOrderForm form = new AddOrderForm();

            AddItemOrderForm addItemOrderForm = new AddItemOrderForm();
            addItemOrderForm.setProductNumber("1733636935");
            addItemOrderForm.setProductName("벨라 파운데이션 그레이 SS 슈퍼싱글 1100X2000");

            form.setItemOrderForms(List.of(addItemOrderForm));

            model.addAttribute("form", form);
        }

        return "addOrder";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute AddOrderForm form) {

        form.getItemOrderForms();

        return "redirect:/v2/orders/add";
    }

}
