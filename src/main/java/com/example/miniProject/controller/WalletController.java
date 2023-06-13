package com.example.miniProject.controller;

import com.example.miniProject.model.Transactions;
import com.example.miniProject.model.Wallets;
import com.example.miniProject.service.WalletsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletsService walletservice;
    @CrossOrigin
    @GetMapping("/getListWallet")
    public List<Wallets> getListWallet(){ return walletservice.getAllList();}
    @CrossOrigin
    @PostMapping("/createNewWallet")
    public String createNew(@RequestBody Wallets wallets) {
        walletservice.createNewWallet(wallets);
        return "success";

    }
    @CrossOrigin
    @PutMapping("/updateWalletById")
    public String updateWallet(@RequestParam("id") int id,@RequestBody Wallets data) {
        System.out.print(id);
        String message = "failded";
        try {
            walletservice.updateWalletById(id, data);
            message = "succes";
        } catch (Exception err) {
            message = "failed" + err ;
        }
        return message;
    }
 @CrossOrigin
    @DeleteMapping("/deleteWalletById/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") int id) {
        try {
            walletservice.deleteWalletById(id);
            return ResponseEntity.ok("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting entity" + e.getMessage());

        }
    }
@CrossOrigin
    @GetMapping("/getListById/{id}")
    public Optional<Wallets> getTransactionById(@PathVariable("id") int id){
        return walletservice.getWalletById(id);
    }



}
