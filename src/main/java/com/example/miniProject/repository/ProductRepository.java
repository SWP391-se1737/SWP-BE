package com.example.miniProject.repository;

import com.example.miniProject.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    List<Products> findByNameContainingIgnoreCase(String searchName);

    @Query("SELECT p FROM Products p WHERE p.categoryid = ?1")
    List<Products> findByCategory_id(int categoryid);

    List<Products> findAllByOrderByCreateATDesc();

    @Query("SELECT p FROM Products p WHERE p.categoryid = :categoryid AND p.sellcampusid = :sellcampusid")
    List<Products> findByCategory_idAndSellCampus_id(@Param("categoryid") int categoryid, @Param("sellcampusid") int sellcampusid);
}
