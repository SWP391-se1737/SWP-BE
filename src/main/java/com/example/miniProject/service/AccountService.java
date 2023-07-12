package com.example.miniProject.service;


import com.example.miniProject.model.Accounts;
import com.example.miniProject.model.Products;
import com.example.miniProject.model.Wallets;
import com.example.miniProject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // create account auto create wallets
    public void addAccount(Accounts acc) {
        try{
            repo.save(acc);
            // create wallet
           /* Wallets wallet = new Wallets();
            wallet.setUserid(acc.getId());
            wallet.setBalance(0);
            WalletsService walletService = new WalletsService();
            walletService.createNewWallet(wallet);*/
        }catch (Exception e){
            throw new RuntimeException("Email already exists");
        }



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
            account.setPassword(newAccount.getPassword());
            return true;
        } else {
            return false;
        }
    }

    public List<Accounts> searchAccountByEmail(String email){
        return repo.findByEmailContainingIgnoreCase(email);
    }

    public  Optional<Accounts> getAccountById(int id){return repo.findById(id);}

    public boolean updateUser(Accounts newAccount, int accountId) {
        Optional<Accounts> existingAccount = repo.findById(accountId);
        if (existingAccount.isPresent()) {
            Accounts account = existingAccount.get();
            account.setPhone(newAccount.getPhone());
            return true;
        } else {
            return false;
        }
    }

    public ResponseEntity<String> login(String email, String password) {
        Optional<Accounts> optionalAccount = repo.findByEmailAndPassword(email, password);
        if (optionalAccount.isPresent()) {
            // Tài khoản hợp lệ, thực hiện các hành động sau khi đăng nhập thành công
            return new ResponseEntity<>("Đăng nhập thành công", HttpStatus.OK);
        } else {
            // Tài khoản không hợp lệ
            return new ResponseEntity<>("Email hoặc mật khẩu không đúng", HttpStatus.UNAUTHORIZED);
        }
    }
}
