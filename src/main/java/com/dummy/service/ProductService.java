package com.dummy.service;

import com.dummy.exception.ResourceNotFoundException;
import com.dummy.model.Product;
import com.dummy.pdf.ProductPDFExporter;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.repository.ProductRepository;
import com.dummy.utils.Mapper;
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
