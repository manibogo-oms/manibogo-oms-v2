package kr.tatine.manibogo_oms_v2.order.ui;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.model.ErrorResult;
import kr.tatine.manibogo_oms_v2.order.command.application.service.PlaceOrderService;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ChargeType;
import kr.tatine.manibogo_oms_v2.order.command.domain.model.vo.ShippingMethod;
import kr.tatine.manibogo_oms_v2.product.command.application.ProductNotFoundException;
import kr.tatine.manibogo_oms_v2.product.query.ProductDao;
import kr.tatine.manibogo_oms_v2.variant.query.VariantDao;
import kr.tatine.manibogo_oms_v2.product.query.ProductDto;
import kr.tatine.manibogo_oms_v2.variant.query.VariantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

@Controller
@RequestMapping("/v2/orders")
@RequiredArgsConstructor
public class PlaceOrderController {

    private final ProductDao productDao;

    private final VariantDao variantDao;

    private final PlaceOrderService placeOrderService;

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

    @ModelAttribute("shippingMethods")
    public ShippingMethod[] shippingMethods() {
        return ShippingMethod.values();
    }

    @ModelAttribute("shippingChargeTypes")
    public ChargeType[] shippingChargeTypes() {
        return ChargeType.values();
    }

    @GetMapping("/place")
    public String getPlaceOrder(Model model) {

        if (!model.containsAttribute("form")) {
            final PlaceOrderForm form = new PlaceOrderForm();
            form.setDispatchDeadline(LocalDateTime.now().plusDays(31).toLocalDate());

            model.addAttribute("form", form);
        }

        return "placeOrder";
    }

    @PostMapping("/place")
    public String placeOrder(
            @ModelAttribute("form") PlaceOrderForm form, Model model) {

        final ErrorResult errorResult = new ErrorResult();

        try {
            placeOrderService.placeOrder(form.toCommand());

        } catch (ValidationErrorException ex) {
            ex.getValidationErrors().forEach(err -> {
                errorResult.rejectValue(err.getName(), err.getErrorCode());
            });
        } catch (ProductNotFoundException ex) {
            errorResult.reject("notFound.product");
        }

        if (errorResult.hasError()) {
            model.addAttribute("errors", errorResult);
            return "placeOrder";
        }

        return "redirect:/v2/orders";
    }

    @PostMapping("/place/selectProduct")
    public String selectProduct(
            @ModelAttribute PlaceOrderForm form,
            @RequestParam String newProductNumber,
            RedirectAttributes redirectAttributes
    ) {

        if (!newProductNumber.isBlank()) {
            form.setProductNumber(newProductNumber);
        }

        redirectAttributes.addFlashAttribute("form", form);

        return "redirect:/v2/orders/place";
    }

}
