package com.example.miniProject.service;

import com.example.miniProject.model.Categories;
import com.example.miniProject.model.Products;
import com.example.miniProject.repository.CategoriesRepository;
import com.example.miniProject.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Products> getAllProduct(){

        return repo.findAll();
    }

    public void createNewProduct(Products product){
            System.out.println(product.getCreateAT());
            repo.save(product);
    }

    public void updateProductById(int id, Products product) {
        Optional<Products> exist = repo.findById(id);
        System.out.println("Exist" + exist);
        if (exist.isPresent()) {
            exist.get().setName(product.getName());
            exist.get().setImage(product.getImage());
            exist.get().setDescription(product.getDescription());
            exist.get().setPrice(product.getPrice());
            exist.get().setCreateAT(product.getCreateAT());
            exist.get().setExpire(product.getExpire());
            exist.get().setStatus(product.getStatus());
            exist.get().setQuantity(product.getQuantity());
            exist.get().setBuycampus_id(product.getBuycampus_id());
            exist.get().setSeller_id(product.getSeller_id());
            exist.get().setSellcampus_id(product.getSellcampus_id());
            exist.get().setCategory_id(product.getCategory_id());

        } else {

            throw new EntityNotFoundException("Not found: " + id);
        }

    }

    public void deleteProductById(int id, Products product){
         Optional<Products> exist = repo.findById(id);
        if(exist.isPresent()) {
            exist.get().setStatus(product.getStatus());
        }else{
            throw new EntityNotFoundException("Not found: " + id);
        }
    }
    public  Optional<Products> getProductById(int   id){return repo.findById(id);}

    public List<Products> searchProductByName(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }

    public List<Products> filterProductByCategory(int category_id){
        return repo.findByCategory_id(category_id);
    }

    public List<Products> filterProductByCampus(int sellcampus_id){
        List<Products> products = repo.findAll();
        List<Products> result = new ArrayList<>();
        for (Products product : products) {
            if (product.getSellcampus_id() == sellcampus_id) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Products> getAllProductOrderByCreate_ATDesc(){
        return repo.findAllByOrderByCreateATDesc();
    }
}
