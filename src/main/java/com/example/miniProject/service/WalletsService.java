package com.example.miniProject.service;


import com.example.miniProject.model.Transactions;
import com.example.miniProject.model.Wallets;
import com.example.miniProject.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WalletsService {
    @Autowired
    private WalletRepository repo;

    public List<Wallets> getAllList() {
        return repo.findAll();
    }

    public void createNewWallet(Wallets data) {
        repo.save(data);
    }

    public void updateWalletById ( int id, Wallets walletUpdate){
        Optional<Wallets> exist = repo.findById(id);
        System.out.println("Exist" + exist);
        if (exist.isPresent()) {
            exist.get().setUserid(walletUpdate.getUserid());
            exist.get().setBalance(walletUpdate.getBalance());
        } else {
            throw new EntityNotFoundException("Not found: " + id);
        }
    }
    public void deleteWalletById(int id){
         repo.deleteById(id);

    }
    public  Optional<Wallets> getWalletById(int id){ return repo.findById(id);}

    public Wallets getWalletByUserId(int userid){ return repo.findByUserid(userid);}


}
