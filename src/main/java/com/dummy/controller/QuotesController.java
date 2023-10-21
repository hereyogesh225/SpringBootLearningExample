package com.dummy.controller;

import com.dummy.model.Quote;
import com.dummy.persistense.entity.QuotesHE;
import com.dummy.service.QuoteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuotesController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public QuotesHE getQuotesHE(@RequestBody Quote quote) {
        return quoteService.saveQuote(quote);
    }

    @GetMapping("/{id}")
    public Quote getQuotes(@PathVariable int id) {
       return quoteService.getQuoteFromDB(id);
    }

    @GetMapping("/")
    public List<Quote> getQuotes() {
        return quoteService.getQuotesFromDB();
    }
}
