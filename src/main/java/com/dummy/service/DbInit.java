package com.dummy.service;

import com.dummy.model.Product;
import com.dummy.model.ProductDetailsResponse;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.entity.QuotesHE;
import com.dummy.persistense.repository.ProductRepository;
import com.dummy.persistense.repository.QuotesRepository;
import com.dummy.utils.Mapper;
import com.dummy.model.Quote;
import com.dummy.model.QuoteDetailsResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DbInit {

    private static final String GET_QUOTES = "https://dummyjson.com/quotes?limit=0";

    private static final String GET_PRODUCTS = "https://dummyjson.com/products?limit=0";
    @Autowired
    private QuotesRepository quotesRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Mapper mapper;

    @PostConstruct
    public void saveQuotesToDB() {
        RestTemplate restTemplate = new RestTemplate();
        saveQuotes(restTemplate);
        saveProducts(restTemplate);
    }

    private void saveQuotes(RestTemplate restTemplate) {
        ResponseEntity<QuoteDetailsResponse> response = restTemplate
                .getForEntity(GET_QUOTES, QuoteDetailsResponse.class);
        QuoteDetailsResponse body = response.getBody();
        List<Quote> quotes = body.getQuotes();
        List<QuotesHE> list = quotes.stream().map(mapper::modelToEntity)
                .collect(Collectors.toList());
        quotesRepository.saveAll(list);
    }

    private void saveProducts(RestTemplate restTemplate) {
        ResponseEntity<ProductDetailsResponse> response = restTemplate
                .getForEntity(GET_PRODUCTS, ProductDetailsResponse.class);
        ProductDetailsResponse body = response.getBody();
        List<Product> quotes = body.getProducts();
        List<ProductHE> list = quotes.stream().map(mapper::modelToEntity)
                .collect(Collectors.toList());
        productRepository.saveAll(list);
    }

    @PreDestroy
    public void deleteRows() {
        quotesRepository.deleteAll();
        productRepository.deleteAll();
    }
}
