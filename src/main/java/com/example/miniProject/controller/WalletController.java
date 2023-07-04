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
    public ResponseEntity<List<Wallets>>getListWallet(){
        List<Wallets> list = walletservice.getAllList();
        return ResponseEntity.status(200).body(list);
    }
    @CrossOrigin
    @PostMapping("/createNewWallet")
    public ResponseEntity<String> createNew(@RequestBody Wallets wallets) {
        try {
            walletservice.createNewWallet(wallets);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating Wallet" + e.getMessage());
        }
    }
    @CrossOrigin
    @PutMapping("/updateWalletById")
    public String updateWallet(@RequestParam("id") int id,@RequestBody Wallets data) {
       try {
           walletservice.updateWalletById(id, data);
           return "Successfully!";
       } catch (EntityNotFoundException e) {
           return "Error updating Wallet" + e.getMessage();
       }
    }
 @CrossOrigin
    @DeleteMapping("/deleteWalletById/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") int id) {
       try {
           walletservice.deleteWalletById(id);
           return ResponseEntity.status(200).body("Delete successfully");
       } catch (Exception e) {
           return ResponseEntity.status(500).body("Error deleting Wallet" + e.getMessage());

       }
    }
@CrossOrigin
    @GetMapping("/getListById/{id}")
    public Optional<Wallets> getTransactionById(@PathVariable("id") int id){
        return walletservice.getWalletById(id);
    }



}
