package com.example.apteka.repositories;

import com.example.apteka.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // klasa odpowiada za odpytywanie bazy danych
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName (String name);
}
