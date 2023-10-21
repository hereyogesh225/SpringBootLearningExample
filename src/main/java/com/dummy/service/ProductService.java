package com.dummy.service;

import com.dummy.model.Product;
import com.dummy.model.ProductDetailsResponse;
import com.dummy.model.Quote;
import com.dummy.model.QuoteDetailsResponse;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private static final String HTTPS_DUMMYJSON_COM_QUOTES = "https://dummyjson.com/products";
    @Autowired
    private RestTemplate restTemplate;
    
    public List<Product> getProducts() {
        ResponseEntity<ProductDetailsResponse> response = restTemplate
                .getForEntity(HTTPS_DUMMYJSON_COM_QUOTES, ProductDetailsResponse.class);

        ProductDetailsResponse body = response.getBody();
        System.out.println("-------------------- " + body.getProducts());
        return body.getProducts();
    }
}
