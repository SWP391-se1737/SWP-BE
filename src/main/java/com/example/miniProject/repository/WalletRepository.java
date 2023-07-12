package com.example.miniProject.repository;

import com.example.miniProject.model.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallets, Integer> {
    Wallets findByUserid(int userid);

}
