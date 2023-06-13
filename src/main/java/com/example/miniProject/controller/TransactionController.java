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
    @CrossOrigin
    @GetMapping("/getListTransaction")
    public List<Transactions> getListTransaction() {
        return transactionsService.getAllList();
    }
    @CrossOrigin
    @PostMapping("/createNewTransactions")
    public String createNew(@RequestBody Transactions data){
        transactionsService.createNewTransactions(data);
        return "success";
    }
    @CrossOrigin
    @PutMapping("/updateTransactionById")
    public String updateTrans(@RequestParam("id") int id,@RequestBody Transactions data) {
        System.out.print(id);
        String message = "failded";
        try {
            transactionsService.updateTransactionsById(id, data);
            message = "succes";
        } catch (Exception err) {
            message = "failed" + err ;
        }
    return message;
    }
    @CrossOrigin
    @DeleteMapping("/deleteTransactionsById/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable("id") int id) {
        try {
            transactionsService.deleteTransactionsById(id);
            return ResponseEntity.ok("Delete successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting entity" + e.getMessage());

        }
    }
    @CrossOrigin
    @GetMapping("/getListById/{id}")
    public Optional<Transactions> getTransactionById(@PathVariable("id") int id){
            return transactionsService.getTransactionById(id);
        }
}

