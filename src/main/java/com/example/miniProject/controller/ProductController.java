package com.example.miniProject.controller;

import com.example.miniProject.model.Products;

import com.example.miniProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
     ProductService productservice;
    @CrossOrigin
    @GetMapping("/getListProduct")
    public ResponseEntity<List<Products>> getListProduct(){
        List<Products> list = productservice.getAllProduct();
        return ResponseEntity.status(200).body(list);
    }
    @CrossOrigin
    @PostMapping("/createNewProduct")
    public String createNewProduct(@RequestBody Products product){
        productservice.createNewProduct(product);
        return "successfully!";
    }

    @CrossOrigin
    @PutMapping("/updateProductById")
    public String updateProduct(@RequestParam("id") int id,@RequestBody Products data) {
        System.out.print(id);
        String message = "failded";
        try {
            productservice.updateProductById(id, data);
            message = "succes";
        } catch (Exception err) {
            message = "failed" + err ;
        }
        return message;
    }
    @CrossOrigin
    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") int id) {
        try {
            productservice.deleteProductById(id);
            return ResponseEntity.ok("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting product" + e.getMessage());

        }
    }
    @CrossOrigin
    @GetMapping("/getProductById")
    public Optional<Products> getTransactionById(@PathVariable("id") int id){
        return productservice.getProductById(id);
    }
    ///////
}
