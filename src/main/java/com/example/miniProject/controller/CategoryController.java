package com.example.miniProject.controller;

import com.example.miniProject.model.Categories;
import com.example.miniProject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listCategory")
    public List<Categories> categoriesList(){
        return categoryService.listCategory();
    }
    @PostMapping("/addCategory")
    public String addCategory(@RequestBody Categories categories){
        categoryService.addCategory(categories);
        return "Campus is added";
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<String> updateCategory(@RequestBody Categories newCategory, @PathVariable int id){
        boolean check = false;
        check = categoryService.updateCategory(newCategory,id);
        try {
            if (check){
                return ResponseEntity.ok("Update Success ID:" + id);
            } else {
                return ResponseEntity.ok("ID: " + id + " khong ton tai");
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error at AccountController:" + e.getMessage());
        }
    }

    @PutMapping("/updateCategoryStatus")
    public ResponseEntity<String> updateCategoryStatus(@RequestParam("id") int id) {
        try {
            boolean check = categoryService.updateCategoryStatus(id);
            if (check) {
                return ResponseEntity.ok("Thay đổi trạng thái thành công cho ID: " + id);
            } else {
                return ResponseEntity.ok("ID " + id + " không tồn tại");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi trong AccountController: " + e.getMessage());
        }
    }
}
