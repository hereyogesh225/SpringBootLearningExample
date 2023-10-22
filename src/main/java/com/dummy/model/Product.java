package com.dummy.model;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class Product {
    private int id;
    private String title;
    private String description;
    private int price;
    private double discountPercentage;
    private double rating;
    private int stock;
    private String brand;
    private String category;
    private String thumbnail;
    private List<String> images;
    private Instant createdDate;
    private Instant updatedDate;
    private UUID createdBy;
    private UUID updatedBy;
}
