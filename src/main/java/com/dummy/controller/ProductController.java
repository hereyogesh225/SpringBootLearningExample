package com.dummy.controller;

import com.dummy.model.Product;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
