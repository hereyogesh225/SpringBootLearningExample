package com.dummy.controller;

import com.dummy.model.Product;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.service.ProductService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> listProducts() {
        return productService.getProducts();
    }

    @GetMapping("query/{query}")
    public List<ProductHE> getProductsByCustomQuery(@PathVariable String query,
            @RequestParam(value = "startPrice", required = false, defaultValue = "100") int startPrice,
            @RequestParam(value = "endPrice", required = false, defaultValue = "100") int endPrice,
            @RequestParam(value = "titleStart", required = false, defaultValue = "Iphone") String titleStart,
            @RequestParam(value = "titleEnd", required = false, defaultValue = "Iphone") String titleEnd,
            @RequestParam(value = "titleIn", required = false, defaultValue = "Iphone") String titleIn) {
        return productService.getProductsByQuery(query, startPrice, endPrice, titleStart, titleEnd, titleIn);
    }

    @GetMapping(value = "pdf")
    public void getAuthorQuotesInPdfFormat(HttpServletResponse response) throws Exception {
        productService.downloadPDF(response);
    }

    @GetMapping(value = "csv")
    public void downloadCSV(HttpServletResponse response) throws Exception {
        productService.downloadCSV(response);
    }

    @GetMapping("category/{category}")
    public List<Product> listProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @DeleteMapping("category/{category}")
    public List<ProductHE> deleteProductsByCategory(@PathVariable String category) {
        return productService.deleteProductsByCategory(category);
    }

    @DeleteMapping("brand/{brand}")
    public List<ProductHE> deleteProductsByBrand(@PathVariable String brand) {
        return productService.removeByBrand(brand);
    }

    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }
}
