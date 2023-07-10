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
    //method này sẽ trả về danh sách các sản phẩm có tên chứa searchName
    @Query("SELECT p FROM Products p WHERE p.categoryid = ?1")
    List<Products> findByCategory_id(int categoryid);
    //method này sẽ trả về danh sách các sản phẩm có categoryid = categoryid
    List<Products> findAllByOrderByCreateATDesc();
    //method này sẽ trả về danh sách các sản phẩm được sắp xếp theo thứ tự giảm dần của createAt
    @Query("SELECT p FROM Products p WHERE p.categoryid = :categoryid AND p.sellcampusid = :sellcampusid")
    List<Products> findByCategory_idAndSellCampus_id(@Param("categoryid") int categoryid, @Param("sellcampusid") int sellcampusid);
    //method này sẽ trả về danh sách các sản phẩm có categoryid = categoryid và sellcampusid = sellcampusid

    @Query("SELECT p FROM Products p WHERE p.seller_id = ?1")
    List<Products> findBySeller_id(int seller_id);
    //method này sẽ trả về danh sách các sản phẩm có seller_id = seller_id



}
