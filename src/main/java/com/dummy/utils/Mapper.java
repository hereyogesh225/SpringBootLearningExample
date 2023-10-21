package com.dummy.utils;

import com.dummy.model.Product;
import com.dummy.model.Quote;
import com.dummy.persistense.entity.ProductHE;
import com.dummy.persistense.entity.QuotesHE;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    @Autowired
    private ModelMapper modelMapper;

    public QuotesHE modelToEntity(Quote quote) {
        return modelMapper.map(quote, QuotesHE.class);
    }

    public ProductHE modelToEntity(Product product) {
        return modelMapper.map(product, ProductHE.class);
    }

    public Quote entityToModel(QuotesHE quotesHE) {
        return modelMapper.map(quotesHE, Quote.class);
    }
}
