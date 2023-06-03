package com.example.miniProject.repository;

import com.example.miniProject.model.Transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository  extends JpaRepository<Transactions,Integer> {
}

