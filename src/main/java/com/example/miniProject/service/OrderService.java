package com.example.miniProject.service;

import com.example.miniProject.model.Orders;
import com.example.miniProject.model.Products;
import com.example.miniProject.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository repo;

    public List<Orders> getAllOrders(){

        return repo.findAll();
    }
    public void createNewOrders(Orders order){
        System.out.println(order.getBuyAt());
        System.out.println(order.getShipAt());
        repo.save(order);
    }

    public void updateOrderById(int id, String status) {
        Optional<Orders> exist = repo.findById(id);
        System.out.println("Exist" + exist);
        if(exist.isPresent()){
            exist.get().setStatus(status);
        } else {
            throw new EntityNotFoundException("Order not found");
        }
    }




    public Optional<Orders> getOrderById(int id){
        return repo.findById(id);
    }

    public List<Orders> getOrderByBuyerId(int buyerid){
        return repo.findByBuyerid(buyerid);
    }
}
