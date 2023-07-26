package com.example.miniProject.repository;

import com.example.miniProject.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    // describe the query to get all products
    List<Products> findByNameContainingIgnoreCase(String searchName);

    // describe the query to get all products by category id
    @Query("SELECT p FROM Products p WHERE p.categoryid = ?1")
    List<Products> findByCategory_id(int categoryid);

    // describe the query to get all products by CreateAT Desc
    List<Products> findAllByOrderByCreateATDesc();

    // describe the query to get all products by sell campus id
    @Query("SELECT p FROM Products p WHERE p.categoryid = :categoryid AND p.sellcampusid = :sellcampusid")
    List<Products> findByCategory_idAndSellCampus_id(@Param("categoryid") int categoryid, @Param("sellcampusid") int sellcampusid);

    // describe the query to get all products by seller id
    @Query("SELECT p FROM Products p WHERE p.seller_id = ?1")
    List<Products> findBySeller_id(int seller_id);




}
