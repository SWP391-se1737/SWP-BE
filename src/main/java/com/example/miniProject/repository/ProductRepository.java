package com.example.miniProject.repository;

import com.example.miniProject.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findByNameContainingIgnoreCase(String searchName);
//    @Query(value = "SELECT * FROM Products WHERE CategoryID = :categoryId")
//    List<Products> findByCategory_ID(Integer category_id);
    @Query("SELECT p FROM Products p WHERE p.category_id = ?1")
    List<Products> findByCategory_id(int category_id);
}
