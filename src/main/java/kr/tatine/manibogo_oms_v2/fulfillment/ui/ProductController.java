package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import jakarta.validation.ConstraintViolationException;
import kr.tatine.manibogo_oms_v2.common.model.*;
import kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.EditProductService;
import kr.tatine.manibogo_oms_v2.fulfillment.command.application.ProductNotFoundException;
import kr.tatine.manibogo_oms_v2.fulfillment.command.domain.product.ProductNameDuplicatedException;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static kr.tatine.manibogo_oms_v2.common.utils.SelectableRowsFormUtils.getRowsFieldName;


@Controller
@RequestMapping("/v2/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    private final EditProductService editProductService;

    @GetMapping
    @Transactional(readOnly = true)
    public String products(Model model) {

        final List<ProductDto> products = productDao.findAll();

        model.addAttribute("products", products);

        initRowsForm(model, products);

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
            } catch (ConstraintViolationException ex) {


            } catch (ProductNotFoundException ex) {
                errorResult.reject("notFound.product", new Object[]{ row.getNumber() });
            } catch (ProductNameDuplicatedException ex) {
                errorResult.rejectValue(getRowsFieldName(i, "name"), "duplicate.product.name");
            }
        }));


        redirectAttributes.addFlashAttribute("response", new CommonResponse("complete.editProducts", errorResult));

        return "redirect:/v2/products";
    }

    private void initRowsForm(Model model, List<ProductDto> products) {
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
