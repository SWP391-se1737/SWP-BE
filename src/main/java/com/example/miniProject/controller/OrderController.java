package com.example.miniProject.controller;

import com.example.miniProject.model.Orders;
import com.example.miniProject.model.Products;
import com.example.miniProject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<String> createNewOrder(Orders order) {
        try {
            orderservice.createNewOrders(order);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating order" + e.getMessage());
        }
    }

    @PutMapping("/updateOrderById")
    public ResponseEntity<String> updateOrderById(int id, Orders order) {
        try {
            orderservice.updateOrderById(id, order);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception err) {
            return ResponseEntity.status(500).body("Error updating order" + err.getMessage());
        }
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") int id, @RequestBody Orders order) {
        try {
            orderservice.deleteOrderById(id,order);
            return ResponseEntity.status(200).body("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting order" + e.getMessage());

        }
    }

    @GetMapping("/getOrderById/{id}")
    public Optional<Orders> getOrderById(@PathVariable("id") int id) {
        return  orderservice.getOrderById(id);
    }

}
