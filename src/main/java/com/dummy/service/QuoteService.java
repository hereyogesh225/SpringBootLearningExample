package com.dummy.service;

import com.dummy.persistense.entity.QuotesHE;
import com.dummy.persistense.repository.QuotesRepository;
import com.dummy.utils.Mapper;
import com.dummy.model.Quote;
import com.dummy.model.QuoteDetailsResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    public static final String HTTPS_DUMMYJSON_COM_QUOTES = "https://dummyjson.com/quotes/";

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
        Optional<QuotesHE> quotesHE = quotesRepository.findById(id);
        if(quotesHE.isPresent()) {
           return mapper.entityToModel(quotesHE.get());
        }
        return null;
    }

    public Quote getQuotes(int id) {
        ResponseEntity<Quote> response = restTemplate
                .getForEntity(HTTPS_DUMMYJSON_COM_QUOTES + id, Quote.class);
        return response.getBody();
    }

    public List<Quote> getQuotesFromDB() {
        List<QuotesHE> quotes = quotesRepository.findAll();
        List<Quote> result = quotes.stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
        return result;
    }

    public List<Quote> getQuotes() {
        ResponseEntity<QuoteDetailsResponse> response = restTemplate
                .getForEntity(HTTPS_DUMMYJSON_COM_QUOTES, QuoteDetailsResponse.class);

        QuoteDetailsResponse body = response.getBody();
        return body.getQuotes();
    }
}
