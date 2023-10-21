package com.dummy.model;

import java.util.List;
import lombok.Data;

@Data
public class ProductDetailsResponse {
    private List<Product> products;
}
