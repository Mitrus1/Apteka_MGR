package com.example.apteka.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity  // oznaczenie klasy, jako tabeli w bazie danych, pola to kolumny
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String producer;
    private String name;
    private int amount;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(){}
}
