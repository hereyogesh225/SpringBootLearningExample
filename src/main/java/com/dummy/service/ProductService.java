package com.dummy.service;

import com.dummy.model.Product;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.repository.ProductRepository;
import com.dummy.utils.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

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
        return productRepository.findById(id)
                .map(mapper::entityToModel)
                .orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findAllByCategory(category)
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    public void deleteProductById(Integer id) {
        productRepository.findById(id)
                .ifPresent(s -> productRepository.deleteById(id));
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
}
