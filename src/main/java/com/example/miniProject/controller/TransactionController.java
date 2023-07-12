package com.example.miniProject.controller;

import com.example.miniProject.model.Transactions;

import com.example.miniProject.service.TransactionsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionsService transactionsService;
    @GetMapping("/getListTransaction")
    public ResponseEntity<List<Transactions>> getListTransaction() {
        List<Transactions> list = transactionsService.getAllList();
        return ResponseEntity.status(200).body(list);
    }

    @PutMapping("/updateTransactionById")
    public ResponseEntity<String> updateTrans(@RequestParam("id") int id,@RequestBody String  status) {
        try {
            transactionsService.updateTransactionsById(id, status);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception err) {
            return ResponseEntity.status(500).body("Error updating Transaction" + err.getMessage());
        }
    }
    @DeleteMapping("/deleteTransactionsById/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") int id) {
        try {
            transactionsService.deleteTransactionsById(id);
            return ResponseEntity.status(200).body("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting Transaction" + e.getMessage());

        }
    }
    @GetMapping("/getListById/{id}")
    public Optional<Transactions> getTransactionById(@PathVariable("id") int id){
            return transactionsService.getTransactionById(id);
        }

    @GetMapping("/getListByWallet_user/{wallet_user}")
     public ResponseEntity<List<Transactions>> getListByWalletId(@PathVariable("wallet_user") int wallet_user){
        List<Transactions> list = transactionsService.getTransactionByWallet_user(wallet_user);
        return ResponseEntity.status(200).body(list);
    }
}

