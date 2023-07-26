package com.example.miniProject.repository;

import com.example.miniProject.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer>{
    @Query("SELECT o FROM Orders o WHERE o.buyerid = ?1 ORDER BY o.buyAt DESC")
    List<Orders> findByBuyerIdAndOrderByBuyAtDesc(int buyerid);

    //List order by BuyAT desc
    List<Orders> findAllByOrderByBuyAtDesc();
}
