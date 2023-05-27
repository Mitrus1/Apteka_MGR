package com.example.apteka.repositories;

import com.example.apteka.model.Assign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // klasa odpowiada za odpytywanie bazy danych
public interface OrderRepository extends JpaRepository<Assign, Long> {
}
