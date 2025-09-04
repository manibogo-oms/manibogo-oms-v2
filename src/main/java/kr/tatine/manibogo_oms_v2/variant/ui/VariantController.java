package kr.tatine.manibogo_oms_v2.variant.ui;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.ui.CommonResponse;
import kr.tatine.manibogo_oms_v2.common.ui.ErrorResult;
import kr.tatine.manibogo_oms_v2.common.utils.RedirectionUtils;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.product.command.application.*;
import kr.tatine.manibogo_oms_v2.product.query.ProductDao;
import kr.tatine.manibogo_oms_v2.product.query.ProductDto;
import kr.tatine.manibogo_oms_v2.variant.command.application.*;
import kr.tatine.manibogo_oms_v2.variant.query.VariantDao;
import kr.tatine.manibogo_oms_v2.variant.query.VariantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils.getRowsFieldName;

@Controller
@RequestMapping("/v2/products/{productNumber}/variants")
@RequiredArgsConstructor
public class VariantController {

    private final ProductDao productDao;

    private final VariantDao variantDao;

    private final EditVariantService editVariantService;

    private final AddVariantService addVariantService;


    @GetMapping
    @Transactional(readOnly = true)
    public String variants(
            @PageableDefault(size = 25) Pageable pageable,
            @PathVariable String productNumber, Model model) {

        final ProductDto productDto = productDao
                .findByNumber(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        model.addAttribute("product", productDto);
        Page<VariantDto> page = variantDao.findAllByProductNumber(pageable, productNumber);

        model.addAttribute("page", page);

        final List<VariantDto> variants = page.getContent();
        model.addAttribute("variants", variants);
        initVariantRowsForm(model, variants);

        return "variants";
    }

    @PostMapping("/edit")
    public String editVariants(
            @PathVariable String productNumber,
            @ModelAttribute VariantRowsForm rowsForm,
            RedirectAttributes redirectAttributes) {

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                editVariantService.edit(row.toCommand());

            } catch (ValidationErrorException ex) {
                ex.getValidationErrors().forEach(error ->
                        errorResult.rejectValue(getRowsFieldName(i, error.getName()), error.getErrorCode()));

            } catch (VariantNotFoundException ex) {
                errorResult.reject("notFound.variant");
            }
        });

        redirectAttributes.addFlashAttribute("response",
                new CommonResponse("complete.editVariants", errorResult));

        final String redirectPath = "/v2/products/%s/variants".formatted(productNumber);

        return "redirect:" + RedirectionUtils.getUrlRemainQuery(redirectPath);
    }

    @GetMapping("/add")
    public String getAddVariant(@PathVariable String productNumber, Model model) {

        final ProductDto productDto = productDao
                .findByNumber(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        final AddVariantForm form = new AddVariantForm();
        form.setProductName(productDto.getName());

        model.addAttribute("form", form);

        return "addVariant";
    }

    @PostMapping("/add")
    public String addVariant(
            @PathVariable String productNumber,
            @ModelAttribute("form") AddVariantForm form,
            Model model
    ) {

        final ErrorResult errorResult = new ErrorResult();

        try {
            addVariantService.add(new VariantCommand(productNumber, form.getKey(), form.getValue(), form.getLabel()));
        } catch (VariantDuplicatedException ex) {
            errorResult.reject("duplicate.variant");
        } catch (ValidationErrorException ex) {
            ex.getValidationErrors().forEach(err -> {
                errorResult.rejectValue(err.getName(), err.getErrorCode());
            });
        }

        if (errorResult.hasError()) {
            model.addAttribute("error", errorResult);
            return "addVariant";
        }

        final String redirectPath = "/v2/products/%s/variants".formatted(productNumber);
        return "redirect:" + RedirectionUtils.getUrlRemainQuery(redirectPath);
    }

    private void initVariantRowsForm(Model model, List<VariantDto> variants) {
        final VariantRowsForm rowsForm = new VariantRowsForm();

        final List<VariantRowsForm.Row> rows = variants.stream()
                .map(VariantRowsForm.Row::fromDto)
                .toList();

        rowsForm.setRows(rows);

        model.addAttribute("rowsForm", rowsForm);
    }
}
