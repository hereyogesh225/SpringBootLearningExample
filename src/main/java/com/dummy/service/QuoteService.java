package com.dummy.service;

import com.dummy.model.Quote;
import com.dummy.persistense.entity.QuotesHE;
import com.dummy.persistense.repository.QuotesRepository;
import com.dummy.utils.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Quote> getQuotesByAuthorNameByOrder(String author, String direction) {
        if("ASC".equalsIgnoreCase(direction)) {
           return quotesRepository.findByAuthor(author, Sort.by(Sort.Direction.ASC,"quote"))
                   .stream().map(mapper::entityToModel).collect(Collectors.toList());
        } else {
            return quotesRepository.findByAuthor(author, Sort.by(Sort.Direction.DESC, "quote"))
                    .stream().map(mapper::entityToModel).collect(Collectors.toList());
        }
    }

    public List<Quote> getQuotesByOrder() {
        return quotesRepository.findAllByOrderByQuoteAsc()
                .stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public List<Quote> getAuthorQuotesAscOrder(String author) {
        return quotesRepository.findByAuthorOrderByQuoteAsc(author)
                .stream().map(mapper::entityToModel).collect(Collectors.toList());
    }

    public List<Quote> findFirst3RecordsByQuoteInDescOrder() {
        return quotesRepository.findFirst3ByOrderByQuoteDesc()
                .stream().map(mapper::entityToModel).collect(Collectors.toList());
    }
}
