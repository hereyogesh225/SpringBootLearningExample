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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
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
        if (size < 100) {
            log.info("deleting all quotes, size is {}", size);
            quotesRepository.deleteAll();
            log.info("quotes deleted, size is {}", size);
            ResponseEntity<QuoteDetailsResponse> response = restTemplate
                    .getForEntity(GET_QUOTES, QuoteDetailsResponse.class);
            QuoteDetailsResponse body = response.getBody();
            List<Quote> quotes = body.getQuotes();
            List<QuotesHE> list = quotes.stream().map(mapper::modelToEntity)
                    .collect(Collectors.toList());
            quotesRepository.saveAll(list);
            log.info("all quotes saved to db, quotes size is {}", list.size());
        }
    }

    @Transactional
    private void saveProducts(RestTemplate restTemplate) {
        int size = productRepository.findAll().size();
        if (size < 100) {
            log.info("deleting all products, size is {}", size);
            productRepository.deleteAll();
            log.info("products deleted, size is {}", size);
            ResponseEntity<ProductDetailsResponse> response = restTemplate
                    .getForEntity(GET_PRODUCTS, ProductDetailsResponse.class);
            ProductDetailsResponse body = response.getBody();
            List<Product> quotes = body.getProducts();
            List<ProductHE> list = quotes.stream().map(mapper::modelToEntity)
                    .collect(Collectors.toList());
            productRepository.saveAll(list);
            log.info("all products saved to db, products size is {}", list.size());
        }
    }

    @PreDestroy
    public void deleteAll() {}
}
