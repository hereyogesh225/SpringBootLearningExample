package com.dummy.model;

import java.util.List;
import lombok.Data;

@Data
public class QuoteDetailsResponse {
    private List<Quote> quotes;
}