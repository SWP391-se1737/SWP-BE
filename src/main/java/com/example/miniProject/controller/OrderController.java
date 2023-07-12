package com.example.miniProject.controller;

import com.example.miniProject.model.Orders;
import com.example.miniProject.model.Products;
import com.example.miniProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderservice;

    @GetMapping("/getListOrder")
    public ResponseEntity<List<Orders>> getListOrder() {
        List<Orders> list = orderservice.getAllOrders();
        return ResponseEntity.status(200).body(list);
    }

    @PostMapping("/createNewOrder")
    public ResponseEntity<String> createNewOrder(@RequestBody Orders order) {
        try {
            orderservice.createNewOrders(order);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating order" + e.getMessage());
        }
    }

    // /updateOrderById/{id}?status=xxx
    @PutMapping("/updateOrderStatusById/{id}")
    public ResponseEntity<String> updateOrderById(@PathVariable("id") int id,@RequestParam String status) {
        try {
            orderservice.updateOrderById(id, status);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception err) {
            return ResponseEntity.status(500).body("Error updating order" + err.getMessage());
        }
    }



    @GetMapping("/getOrderById/{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") int id) {
        return  orderservice.getOrderById(id);
    }

    @GetMapping("/getOrderByBuyerId/{buyerid}")
    public List<Orders> getOrderByBuyerId(@PathVariable("buyerid") int buyerid) {
        return orderservice.getOrderByBuyerId(buyerid);
    }

}
