package com.example.miniProject.repository;

import com.example.miniProject.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Integer> {
    List<Accounts> findByEmailContainingIgnoreCase(String searchEmail);

    @Query("SELECT a FROM Accounts a WHERE a.email = :email AND a.password = :password")
    Optional<Accounts> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
