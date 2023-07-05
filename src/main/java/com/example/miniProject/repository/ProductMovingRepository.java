package com.example.miniProject.repository;

import com.example.miniProject.model.ProductMovings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMovingRepository extends JpaRepository<ProductMovings, Integer>{
}
