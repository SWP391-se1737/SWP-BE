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
    public void updateTransactionsById ( int id, String status){
        Optional<Transactions> exist = repo.findById(id);
        System.out.println("Exist" + exist);
        if (exist.isPresent()) {
            exist.get().setStatus(status);
            repo.save(exist.get());
        } else {
            throw new EntityNotFoundException("Not found: " + id);
        }


   }
   public void deleteTransactionsById(int entityId){
       repo.deleteById(entityId);

   }
   public  Optional<Transactions> getTransactionById(int id){ return repo.findById(id);}

    public List<Transactions> getTransactionByWallet_user(int wallet_user){
        return repo.findByWallet_userOrderByTransactionDatetimeDesc(wallet_user);
    }

    public List<Transactions> getTransactionByDateTime(){
        return repo.findAllByOrderByTransaction_dateTimeDesc();
    }

}

