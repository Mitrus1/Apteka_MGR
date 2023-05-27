package com.example.apteka.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity  // oznaczenie klasy, jako tabeli w bazie danych, pola to kolumny
public class Assign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "assign_product",
            joinColumns = @JoinColumn(name = "assign_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private List<Product> products = new ArrayList<>();
    @Column (name = "sum_price")
    private double sumPrice;
    @Column (name = "finish_time")
    private LocalDateTime finishTime;
    public Assign() {
    }
}
