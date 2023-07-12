package com.example.miniProject.controller;

import com.example.miniProject.model.ProductMovings;
import com.example.miniProject.service.ProductMovingService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productMoving")
public class ProductMovingController {
    @Autowired
    private ProductMovingService productMovingService;

    @GetMapping("/getListProductMoving")
    public ResponseEntity<List<ProductMovings>> getListProductMoving(){
        List<ProductMovings> list = productMovingService.getAllProductMoving();
        return ResponseEntity.status(200).body(list);
    }
    @PostMapping("/createNewProductMoving")
    public ResponseEntity<String> createNewProductMoving(ProductMovings productMoving){
        try{
            productMovingService.createNewProductMoving(productMoving);
            return ResponseEntity.status(200).body("Successfully!");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error creating productMoving" + e.getMessage());
        }
    }
    @PutMapping("/updateProductMovingByMovingId")
    public ResponseEntity<String> updateProductMovingByMovingId(int MovingId, @RequestBody String status){
        try{
            productMovingService.updateProductMovingByMovingId(MovingId, status);
            return ResponseEntity.status(200).body("Successfully!");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error updating productMoving" + e.getMessage());
        }
    }

    @DeleteMapping("/deleteProductMovingByMovingId/{MovingId}")
    public ResponseEntity<String> deleteProductMovingByMovingId(@PathVariable("MovingId") int MovingId){
        try{
            productMovingService.deleteProductMovingByMovingId(MovingId);
            return ResponseEntity.status(200).body("Delete successfully");
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error deleting productMoving" + e.getMessage());
        }
    }
    @GetMapping("/getProductMovingByMovingId/{MovingId}")
    public Optional<ProductMovings> getProductMovingByMovingId(@PathVariable("MovingId") int MovingId){
        return productMovingService.getProductMovingByMovingId(MovingId);
    }
}
