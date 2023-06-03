package com.example.miniProject.service;

import com.example.miniProject.model.Transactions;

import com.example.miniProject.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class TransactionsService {

    @Autowired
    private TransactionRepository repo;

   public List<Transactions> getAllList() {
       return repo.findAll();
   }

   public void createNewTransactions(Transactions data){
        repo.save(data);


   }

}

