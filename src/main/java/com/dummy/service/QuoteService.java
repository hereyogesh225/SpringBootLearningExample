package com.dummy.service;

import com.dummy.model.Quote;
import com.dummy.pdf.QuotePDFExporter;
import com.dummy.persistense.entity.QuotesHE;
import com.dummy.persistense.repository.QuotesRepository;
import com.dummy.utils.Mapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

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
        return quotes.stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());
    }

    public List<Quote> getQuotesByAuthorNameByOrder(String author, String direction) {
        Sort.Direction directionEnum = Sort.Direction.fromString(direction);
        return quotesRepository.findByAuthor(author, Sort.by(directionEnum, "quote"))
                .stream().map(mapper::entityToModel).collect(Collectors.toList());
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

    public void downloadPDF(HttpServletResponse httpServletResponse) throws IOException {
        QuotePDFExporter quotePDFExporter = new QuotePDFExporter(getQuotesFromDB());
        quotePDFExporter.downLoadPdf(httpServletResponse);
    }

    public void downloadCSV(HttpServletResponse httpServletResponse) throws Exception {
        QuotePDFExporter quotePDFExporter = new QuotePDFExporter(getQuotesFromDB());
        quotePDFExporter.downloadCSV(httpServletResponse);
    }
}
