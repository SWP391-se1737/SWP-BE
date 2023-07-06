package com.example.miniProject.repository;

import com.example.miniProject.model.Deposits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositRepository extends JpaRepository<Deposits, Integer> {
}
