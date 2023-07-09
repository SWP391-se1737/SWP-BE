package com.example.miniProject.controller;

import com.example.miniProject.model.Products;
import com.example.miniProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productservice;

    @GetMapping("/getListProduct")
    public ResponseEntity<List<Products>> getListProduct() {
        List<Products> list = productservice.getAllProduct();
        return ResponseEntity.status(200).body(list);
    }

    @PostMapping("/createNewProduct")
    public ResponseEntity<String> createNewProduct(@RequestBody Products product) {
        try {
        productservice.createNewProduct(product);
        return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating product" + e.getMessage());
        }
    }

    @PutMapping("/updateProductById")
    public ResponseEntity<String> updateProduct(@RequestParam("id") int id, @RequestBody Products data) {
        try {
            productservice.updateProductById(id, data);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception err) {
            return ResponseEntity.status(500).body("Error updating product" + err.getMessage());
        }
    }

    @CrossOrigin
    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable("id") int id, @RequestBody Products product) {
        try {
            productservice.deleteProductById(id,product);
            return ResponseEntity.status(200).body("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting product" + e.getMessage());

        }
    }

    @GetMapping("/getProductById/{id}")
    public Optional<Products> getTransactionById(@PathVariable("id") int id) {
        return  productservice.getProductById(id);
    }

    @GetMapping("/searchProductByName")
    public ResponseEntity<List<Products>> searchProductByName(@RequestParam("name") String name) {
        List<Products> list = productservice.searchProductByName(name);
        return ResponseEntity.status(200).body(list);
    }

    @GetMapping("/filterProductByCategory")
    public ResponseEntity<List<Products>> filterProductByCategory(@RequestParam("categoryid") int categoryid) {
        return ResponseEntity.status(200).body(productservice.filterProductByCategory(categoryid));
    }

    @GetMapping("/filterProductByCampus")
    public ResponseEntity<List<Products>> filterProductByCampus(@RequestParam("sellcampusid") int sellcampusid) {
        return ResponseEntity.status(200).body(productservice.filterProductByCampus(sellcampusid));
    }

    @GetMapping("/getProductByCreateAtDesc")
    public ResponseEntity<List<Products>> getProductByCreateAtDesc() {
        return ResponseEntity.status(200).body(productservice.getAllProductOrderByCreate_ATDesc());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Products>> getProductsByCategory_idAndSellCampus( @RequestParam("category_id") int  category_id, @RequestParam("sellcampus_id") int sellcampus_id) {
        return ResponseEntity.status(200).body(productservice.getProductsByCategory_idAndSellCampus_id(category_id, sellcampus_id));
    }

    @GetMapping("/getProductBySellerId")
    public ResponseEntity<List<Products>> getProductBySellerId(@RequestParam("seller_id") int seller_id) {
        return ResponseEntity.status(200).body(productservice.getProductBySellerId(seller_id));
    }

}
