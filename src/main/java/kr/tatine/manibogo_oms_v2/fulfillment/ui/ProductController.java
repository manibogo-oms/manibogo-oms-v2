package kr.tatine.manibogo_oms_v2.fulfillment.ui;

import kr.tatine.manibogo_oms_v2.fulfillment.query.dao.ProductDao;
import kr.tatine.manibogo_oms_v2.fulfillment.query.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/v2/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @GetMapping
    @Transactional(readOnly = true)
    public String products(Model model) {

        final List<ProductDto> products = productDao.findAll();

        model.addAttribute("products", products);

        initRowsForm(model, products);

        return "products";
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
        row.setProductNumber(productDto.getNumber());
        row.setProductName(productDto.getName());
        row.setPriority(productDto.getPriority());

        return row;
    }

}
