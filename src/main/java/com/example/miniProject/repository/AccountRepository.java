package com.example.miniProject.repository;

import com.example.miniProject.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts,Integer> {
}
