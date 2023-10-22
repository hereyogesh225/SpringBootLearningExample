package com.dummy.service;

import com.dummy.model.Quote;
import com.dummy.persistense.entity.QuotesHE;
import com.dummy.persistense.repository.QuotesRepository;
import com.dummy.utils.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    public static final String GET_QUOTES = "https://dummyjson.com/quotes/";

    @Autowired
    private Mapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private QuotesRepository quotesRepository;

    public QuotesHE saveQuote(Quote quote) {
        QuotesHE quotesHE = mapper.modelToEntity(quote);
        return quotesRepository.save(quotesHE);
    }

    public Quote getQuoteFromDB(int id) {
        return quotesRepository.findById(id)
                .map(mapper::entityToModel)
                .orElse(null);
    }

    public List<Quote> getQuotesFromDB() {
        List<QuotesHE> quotes = quotesRepository.findAll();
        List<Quote> result = quotes.stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
        return result;
    }
}
