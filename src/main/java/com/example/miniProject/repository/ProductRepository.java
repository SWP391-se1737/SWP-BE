package com.example.miniProject.repository;

import com.example.miniProject.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findByNameContainingIgnoreCase(String searchName);
//    List<Products> findByCateGoryId(int categoryId);
}
