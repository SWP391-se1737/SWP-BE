package com.example.miniProject.repository;

import com.example.miniProject.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer>{
    List<Orders> findByBuyerid(int buyerid);

    //List order by BuyAT desc
    List<Orders> findAllByOrderByBuyAtDesc();
}
