package com.example.apteka.repositories;

import com.example.apteka.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // klasa odpowiada za odpytywanie bazy danych
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.category.name) LIKE " +
            "LOWER(CONCAT('%', :category, '%') )AND p.amount>0")
    List<Product> findAllByCategoryName (String category);

    @Query( "SELECT p FROM Product p WHERE LOWER(p.category.name) LIKE " +
            "LOWER(CONCAT('%', :category, '%') )AND p.amount>0"+
            "AND LOWER(p.producer) LIKE LOWER(CONCAT('%', :producer, '%'))")
    List<Product> findAllByProducer (String producer);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%') ) "+
            "AND p.amount>0")
    List<Product> findAllByName (String name);

}
