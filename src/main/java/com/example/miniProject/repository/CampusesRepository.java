package com.example.miniProject.repository;
import com.example.miniProject.model.Campuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampusesRepository extends JpaRepository<Campuses, Integer> {
}
