package com.example.miniProject.repository;

import com.example.miniProject.model.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposits, Integer> {
}
