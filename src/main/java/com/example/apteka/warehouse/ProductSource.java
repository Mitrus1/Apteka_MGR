package com.example.apteka.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSource {
    private Long id;
    private String producer;
    private String name;
    private int amount;
    private double price;
}
