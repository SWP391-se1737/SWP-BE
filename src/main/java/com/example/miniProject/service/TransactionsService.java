package com.example.miniProject.service;

import com.example.miniProject.model.Transactions;

import com.example.miniProject.repository.TransactionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionsService {

    @Autowired
    private TransactionRepository repo;

   public List<Transactions> getAllList() {
       return repo.findAll();
   }

   public void createNewTransactions(Transactions data) {
       repo.save(data);
   }
    public void updateTransactionsById ( int id, Transactions transUpdate){
       Optional<Transactions> exist = repo.findById(id);
       System.out.println("Exist" + exist);
       if (exist.isPresent()) {
           exist.get().setAmount(transUpdate.getAmount());
           exist.get().setExchange_id(transUpdate.getExchange_id());
           exist.get().setStatus(transUpdate.isStatus());
           exist.get().setOrder_id(transUpdate.getOrder_id());
           exist.get().setWallet_id(transUpdate.getWallet_id());
       } else {
           throw new EntityNotFoundException("Not found: " + id);
       }
   }
   public void deleteTransactionsById(int entityId){
       repo.deleteById(entityId);

   }
   public  Optional<Transactions> getTransactionById(int id){ return repo.findById(id);}

    public List<Transactions> getTransactionByWalletId(int wallet_id){
        return repo.findByWallet_id(wallet_id);
    }

}

