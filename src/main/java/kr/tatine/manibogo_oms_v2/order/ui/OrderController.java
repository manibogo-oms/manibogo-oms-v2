package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.product.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.product.query.dao.VariantDao;
import kr.tatine.manibogo_oms_v2.product.query.dto.ProductDto;
import kr.tatine.manibogo_oms_v2.product.query.dto.VariantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class OrderController {

    private final ProductDao productDao;

    private final VariantDao variantDao;

    @ModelAttribute("products")
    public Map<String, ProductDto> products() {
        return productDao.findAll().stream()
                .collect(toMap(ProductDto::getNumber, Function.identity()));
    }

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
            model.addAttribute("form", form);
        }

        return "addOrder";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute AddOrderForm form) {

        form.getItemOrderForms();

        return "redirect:/v2/orders/add";
    }

    @PostMapping("/add/addItemOrder")
    public String addItemOrder(
            @ModelAttribute AddOrderForm form,
            @RequestParam String newProductNumber,
            @RequestParam Integer newAmount,
            RedirectAttributes redirectAttributes
    ) {

        if (!newProductNumber.isBlank()) {
            final List<AddItemOrderForm> itemOrderForms = new ArrayList<>(form.getItemOrderForms());

            final AddItemOrderForm itemOrderForm = new AddItemOrderForm();
            itemOrderForm.setProductNumber(newProductNumber);

            if (Objects.isNull(newAmount)) {
                newAmount = 1;
            }
            itemOrderForm.setAmount(newAmount);

            itemOrderForms.add(itemOrderForm);

            form.setItemOrderForms(itemOrderForms);
        }

        redirectAttributes.addFlashAttribute("form", form);

        return "redirect:/v2/orders/add#lastItemOrder";
    }

}
