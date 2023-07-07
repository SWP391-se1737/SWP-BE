package com.example.miniProject.service;


import com.example.miniProject.model.Accounts;
import com.example.miniProject.model.Products;
import com.example.miniProject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository repo;

    public List<Accounts> listAccount() {
        return repo.findAll();
    }

    public void addAccount(Accounts acc) {
        repo.save(acc);
    }

    public boolean deleteAccount(int accID) {
        if (repo.existsById(accID)) {
            repo.deleteById(accID);
            return true;
        } else {
            return false;
        }
    }


    public boolean updateAccount(Accounts newAccount, int accountId) {
        Optional<Accounts> existingAccount = repo.findById(accountId);
        if (existingAccount.isPresent()) {
            Accounts account = existingAccount.get();
            account.setEmail(newAccount.getEmail());
            account.setPhone(newAccount.getPhone());
            account.setRole(newAccount.getRole());
            account.setStatus(newAccount.isStatus());
            return true;
        } else {
            return false;
        }
    }
    public List<Accounts> searchAccountByEmail(String email){
        return repo.findByEmailContainingIgnoreCase(email);
    }
}
