package com.dummy.service;

import com.dummy.model.Product;
import com.dummy.model.ProductDetailsResponse;
import com.dummy.model.Quote;
import com.dummy.model.QuoteDetailsResponse;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.entity.QuotesHE;
import com.dummy.persistense.repository.ProductRepository;
import com.dummy.persistense.repository.QuotesRepository;
import com.dummy.utils.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
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

    @Transactional
    private void saveQuotes(RestTemplate restTemplate) {
        int size = quotesRepository.findAll().size();
        if (size < 1 || size < 100) {
            quotesRepository.deleteAll();
            ResponseEntity<QuoteDetailsResponse> response = restTemplate
                    .getForEntity(GET_QUOTES, QuoteDetailsResponse.class);
            QuoteDetailsResponse body = response.getBody();
            List<Quote> quotes = body.getQuotes();
            List<QuotesHE> list = quotes.stream().map(mapper::modelToEntity)
                    .collect(Collectors.toList());
            quotesRepository.saveAll(list);
        }
    }

    @Transactional
    private void saveProducts(RestTemplate restTemplate) {
        int size = productRepository.findAll().size();
        if (size < 1 || size < 100) {
            productRepository.deleteAll();
            ResponseEntity<ProductDetailsResponse> response = restTemplate
                    .getForEntity(GET_PRODUCTS, ProductDetailsResponse.class);
            ProductDetailsResponse body = response.getBody();
            List<Product> quotes = body.getProducts();
            List<ProductHE> list = quotes.stream().map(mapper::modelToEntity)
                    .collect(Collectors.toList());
            productRepository.saveAll(list);
        }
    }

    @PreDestroy
    public void deleteAll() {}
}
