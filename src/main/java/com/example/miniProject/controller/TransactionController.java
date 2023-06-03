package com.example.miniProject.controller;

import com.example.miniProject.model.Transactions;

import com.example.miniProject.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionsService transactionsService;

    @GetMapping("/getListTransaction")
    public List<Transactions> getListTransaction() {
        return transactionsService.getAllList();
    }
    @PostMapping("/createNewTransactions")
    public String createNew(@RequestBody Transactions data){
        transactionsService.createNewTransactions(data);
        return "success";

    }

}
