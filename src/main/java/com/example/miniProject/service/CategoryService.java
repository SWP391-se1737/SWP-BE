package com.example.miniProject.service;


import com.example.miniProject.model.Categories;
import com.example.miniProject.repository.CategoriesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoriesRepository repo;
    public List<Categories> listCategory() {
        return repo.findAll();
    }

    public void addCategory(Categories categories) {
        repo.save(categories);
    }

    public boolean deleteCategory(int cateID) {
        if (repo.existsById(cateID)) {
            repo.deleteById(cateID);
            return true;
        } else {
            return false;
        }
    }


    public boolean updateCategory(Categories newCategory, int cateId) {
        Optional<Categories> existingCategory = repo.findById(cateId);
        if (existingCategory.isPresent()) {
            Categories categories = existingCategory.get();
            categories.setName(newCategory.getName());
            categories.setStatus(newCategory.isStatus());

            return true;
        } else {
            return false;
        }
    }
//    public Categories searchCategoryByName(String name ){return repo.searchByName(name);}
}
