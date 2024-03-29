package com.example.miniProject.repository;

import com.example.miniProject.model.MovingItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovingItemRepository extends JpaRepository<MovingItems, Integer> {
    List<MovingItems> findById(int id);
}
