package com.example.miniProject.controller;

import com.example.miniProject.model.Accounts;
import com.example.miniProject.model.Products;
import com.example.miniProject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

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


    @GetMapping("/searchAccountByName")
    public ResponseEntity<Map<String, Object>> searchAccountByEmail(@RequestParam("email") String email) {
        List<Accounts> list = accountService.searchAccountByEmail(email);
        Map<String, Object> response = new HashMap<>();

        if (list != null && !list.isEmpty()) {
            // Tài khoản đã tồn tại
            response.put("exists", true);
            response.put("account", list.get(0)); // Lấy tài khoản đầu tiên trong danh sách
        } else {
            // Tài khoản không tồn tại
            response.put("exists", false);
            response.put("account", null);
        }

        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/getAccountById")
    public Optional<Accounts> getTransactionById(@RequestParam("id") int id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<String> updateUser(@RequestBody Accounts newAcc, @PathVariable int id){
        boolean check = false;
        check = accountService.updateUser(newAcc,id);
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("Email") String email, @RequestParam("Password") String password) {
        return accountService.login(email, password);
    }
}
