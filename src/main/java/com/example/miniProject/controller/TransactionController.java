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
    @PostMapping("/createNewTransactions")
    public ResponseEntity<String> createNew(@RequestBody Transactions data){
        try {
            transactionsService.createNewTransactions(data);
            return ResponseEntity.status(200).body("Successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating Transaction" + e.getMessage());
        }
    }
    @PutMapping("/updateTransactionById")
    public ResponseEntity<String> updateTrans(@RequestParam("id") int id,@RequestBody Transactions data) {
        try {
            transactionsService.updateTransactionsById(id, data);
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
}

