package com.dummy.utils;

import com.dummy.model.Product;
import com.dummy.model.Quote;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.entity.QuotesHE;
import java.time.Instant;
import java.util.UUID;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public QuotesHE modelToEntity(Quote quote) {
        QuotesHE quotesHE = modelMapper.map(quote, QuotesHE.class);
        quotesHE.setCreatedDate(Instant.now());
        quotesHE.setUpdatedDate(Instant.now());
        quotesHE.setCreatedBy(UUID.randomUUID());
        quotesHE.setUpdatedBy(UUID.randomUUID());
        return quotesHE;
    }

    public Quote entityToModel(QuotesHE quotesHE) {
        return modelMapper.map(quotesHE, Quote.class);
    }

    public ProductHE modelToEntity(Product product) {
        ProductHE productHE = modelMapper.map(product, ProductHE.class);
        productHE.setCreatedDate(Instant.now());
        productHE.setUpdatedDate(Instant.now());
        productHE.setCreatedBy(UUID.randomUUID());
        productHE.setUpdatedBy(UUID.randomUUID());
        return productHE;
    }

    public Product entityToModel(ProductHE productHE) {
        return modelMapper.map(productHE, Product.class);
    }
}
