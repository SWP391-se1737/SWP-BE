package com.example.miniProject.repository;

import com.example.miniProject.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
//    Categories searchByName(String searchCategory);
}
