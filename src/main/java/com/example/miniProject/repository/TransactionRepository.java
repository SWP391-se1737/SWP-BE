package com.example.miniProject.repository;

import com.example.miniProject.model.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TransactionRepository  extends JpaRepository<Transactions,Integer> {

        @Query("SELECT t FROM Transactions t WHERE t.wallet_user = ?1 ORDER BY t.transaction_datetime DESC")
        List<Transactions> findByWallet_userOrderByTransactionDatetimeDesc(int wallet_user);
        @Query("SELECT t FROM Transactions t ORDER BY t.transaction_datetime DESC")
        List<Transactions> findAllByOrderByTransaction_dateTimeDesc();
}

