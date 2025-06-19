package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.ValidationErrorException;
import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.common.utils.RedirectionUtils;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.*;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNameDuplicatedException;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.VariantDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.VariantDto;
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
@RequestMapping("/v2/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    private final VariantDao variantDao;

    private final EditProductService editProductService;

    private final EditVariantService editVariantService;

    private final AddVariantService addVariantService;

    @GetMapping
    @Transactional(readOnly = true)
    public String products(Model model,
                           @PageableDefault(size = 25) Pageable pageable) {

        final Page<ProductDto> page = productDao.findAll(pageable);

        model.addAttribute("products", page.getContent());
        model.addAttribute("page", page);

        initProductRowsForm(model, page.getContent());

        return "products";
    }

    @PostMapping("/edit")
    public String editProducts(
            @ModelAttribute ProductRowsForm rowsForm,
            RedirectAttributes redirectAttributes
    ) {

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, ((i, row) -> {
            try {
                editProductService.editProduct(row.toEditCommand());
            } catch (ValidationErrorException ex) {
                ex.getValidationErrors().forEach(error ->
                        errorResult.rejectValue(getRowsFieldName(i, error.getName()), error.getErrorCode()));

            } catch (ProductNotFoundException ex) {
                errorResult.reject("notFound.product", new Object[]{ row.getNumber() });
            } catch (ProductNameDuplicatedException ex) {
                errorResult.rejectValue(getRowsFieldName(i, "name"), "duplicate.product.name");
            }
        }));

        redirectAttributes.addFlashAttribute("response", new CommonResponse("complete.editProducts", errorResult));

        return "redirect:" + RedirectionUtils.getUrlRemainQuery("/v2/products");
    }

    @GetMapping("/{productNumber}/variants")
    @Transactional(readOnly = true)
    public String variants(
            @PageableDefault(size = 25) Pageable pageable,
            @PathVariable String productNumber,
            Model model
    ) {

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

    @PostMapping("/{productNumber}/variants/edit")
    public String editVariants(
            @PathVariable String productNumber,
            @ModelAttribute VariantRowsForm rowsForm,
            RedirectAttributes redirectAttributes) {

        final ErrorResult errorResult = new ErrorResult();

        SelectableRowsFormUtils.handle(rowsForm, errorResult, (i, row) -> {
            try {
                editVariantService.edit(row.toEditCommand());

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

    @GetMapping("/{productNumber}/variants/add")
    public String getAddVariant(@PathVariable String productNumber, Model model) {

        final ProductDto productDto = productDao
                .findByNumber(productNumber)
                .orElseThrow(ProductNotFoundException::new);

        final AddVariantForm form = new AddVariantForm();
        form.setProductName(productDto.getName());

        model.addAttribute("form", form);

        return "addVariant";
    }

    @PostMapping("/{productNumber}/variants/add")
    public String addVariant(
            @PathVariable String productNumber,
            @ModelAttribute("form") AddVariantForm form,
            Model model
    ) {

        final ErrorResult errorResult = new ErrorResult();

        try {
            addVariantService.add(new AddVariantCommand(productNumber, form.getKey(), form.getValue(), form.getLabel()));
        } catch (ValidationErrorException ex) {
            ex.getValidationErrors().forEach(err -> {
                if (err.getName() == null) {
                    errorResult.reject(err.getErrorCode());
                    return;
                }
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

    private void initProductRowsForm(Model model, List<ProductDto> products) {
        final ProductRowsForm rowsForm = new ProductRowsForm();

        final List<ProductRowsForm.Row> rows = products.stream().map(this::converToRow).toList();
        rowsForm.setRows(rows);

        model.addAttribute("rowsForm", rowsForm);
    }

    private ProductRowsForm.Row converToRow(ProductDto productDto) {
        final ProductRowsForm.Row row = new ProductRowsForm.Row();

        row.setIsSelected(false);
        row.setNumber(productDto.getNumber());
        row.setName(productDto.getName());
        row.setPriority(productDto.getPriority());
        row.setIsEnabled(productDto.getIsEnabled());

        return row;
    }




}
