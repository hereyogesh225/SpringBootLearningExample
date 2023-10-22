package com.dummy.model;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class Quote {
    private int id;
    private String quote;
    private String author;
    private Instant createdDate;
    private Instant updatedDate;
    private UUID createdBy;
    private UUID updatedBy;
}