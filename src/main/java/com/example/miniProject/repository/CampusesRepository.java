package com.example.miniProject.repository;
import com.example.miniProject.model.Campuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusesRepository extends JpaRepository<Campuses, Integer> {
}
