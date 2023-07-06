package com.example.miniProject.controller;

import com.example.miniProject.model.Accounts;
import com.example.miniProject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Account")


public class AccountController {
    @Autowired
    private AccountService accountService;
    @GetMapping("/listAccount")
    public List<Accounts> accountList(){
        return accountService.listAccount();
    }


    @PostMapping("/addAccount")
    public String addAccount(@RequestBody Accounts acc){
        accountService.addAccount(acc);
        return "Account is added";
    }


    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id){
        boolean check = false;
        check = accountService.deleteAccount(id);
        try {
            if(check){
                return ResponseEntity.ok("Delete Success Id: " + id);
            }else{
                return ResponseEntity.ok("ID " + id + " khong ton tai");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error at AccountController:" + e.getMessage());
        }
    }
    @PutMapping("/updateAccount/{id}")
    public ResponseEntity<String> updateAccount(@RequestBody Accounts newAcc, @PathVariable int id){
        boolean check = false;
        check = accountService.updateAccount(newAcc,id);
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
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String username, @RequestParam String password) {
        // Thực hiện xác thực đăng nhập
        // Kiểm tra thông tin tài khoản
        if (username.equals("admin") && password.equals("password")) {
            Map<String, String> response = Collections.singletonMap("message", "Đăng nhập thành công");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = Collections.singletonMap("message", "Sai tên đăng nhập hoặc mật khẩu");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
