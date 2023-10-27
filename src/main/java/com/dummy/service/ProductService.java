package com.dummy.service;

import com.dummy.exception.ResourceNotFoundException;
import com.dummy.model.Product;
import com.dummy.pdf.ProductPDFExporter;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.repository.ProductRepository;
import com.dummy.utils.Mapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static final String PRODUCT_WITH_ID_NOT_FOUND = "Product with id %d not found";

    @Autowired
    private Mapper mapper;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    public List<ProductHE> getProductsByQuery(String query, int startPrice, int endPrice,
            String titleStart, String titleEnd, String titleIn) {
        List<ProductHE> list = null;
        switch (query) {
            case "priceBetween":
                list = productRepository.findByPriceBetween(startPrice, endPrice);
                break;
            case "priceLessThan":
                list = productRepository.findByPriceLessThan(endPrice);
                break;
            case "priceLessThanEqual":
                list = productRepository.findByPriceLessThanEqual(endPrice);
                break;
            case "priceGreaterThan":
                list = productRepository.findByPriceGreaterThan(startPrice);
                break;
            case "priceGreaterThanEqual":
                list = productRepository.findByPriceGreaterThanEqual(startPrice);
                break;
            case "priceIn":
                list = productRepository.findByPriceIn(Arrays.asList(startPrice, endPrice));
                break;
            case "titleStartingWith":
                list = productRepository.findByTitleStartingWith(titleStart);
                break;
            case "titleEndingWith":
                list = productRepository.findByTitleEndingWith(titleEnd);
                break;
            case "titleContaining":
                list = productRepository.findByTitleContaining(titleIn);
                break;
        }
        return list;
    }

    public Product getProductById(int id) {
        ResourceNotFoundException resourceNotFoundException =
                new ResourceNotFoundException(String.format(PRODUCT_WITH_ID_NOT_FOUND, id));
        return productRepository.findById(id)
                .map(mapper::entityToModel)
                .orElseThrow(() -> resourceNotFoundException);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAllByCategory(category)
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    public void deleteProductById(Integer id) {
        ResourceNotFoundException resourceNotFoundException =
                new ResourceNotFoundException(String.format(PRODUCT_WITH_ID_NOT_FOUND, id));
        Optional<ProductHE> optionalProductHE = productRepository.findById(id);
        optionalProductHE.orElseThrow(() -> resourceNotFoundException);
        optionalProductHE.ifPresent(s -> productRepository.deleteById(id));
    }

    @Transactional
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    @Transactional
    public List<ProductHE> deleteProductsByCategory(String category) {
        return productRepository.deleteByCategory(category);
    }

    @Transactional
    public List<ProductHE> removeByBrand(String brand) {
        return productRepository.removeByBrand(brand);
    }

    public void downloadPDF(HttpServletResponse response) throws Exception {
        ProductPDFExporter productPDFExporter = new ProductPDFExporter(getProducts());
        productPDFExporter.downloadPDF(response);
    }

    public void downloadCSV(HttpServletResponse response) throws Exception {
        ProductPDFExporter productPDFExporter = new ProductPDFExporter(getProducts());
        productPDFExporter.downloadCSV(response);
    }
}
