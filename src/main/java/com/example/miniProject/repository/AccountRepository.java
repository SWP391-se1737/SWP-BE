package com.example.miniProject.repository;

import com.example.miniProject.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Integer> {
    List<Accounts> findByEmailContainingIgnoreCase(String searchEmail);
}
