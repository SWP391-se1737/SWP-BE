package com.example.miniProject.service;

import com.example.miniProject.model.Products;
import com.example.miniProject.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public List<Products> getAllProduct(){ return repo.findAll();}

    public void createNewProduct(Products product){
         repo.save(product);
    }
    public void updateProductById( int id,Products product){
        Optional<Products> exist = repo.findById(id);
        System.out.println("Exist" +exist);
        if(exist.isPresent()){
            exist.get().setName(product.getName());
            exist.get().setImage(product.getImage());
            exist.get().setDescription(product.getDescription());
            exist.get().setPrice(product.getPrice());
            exist.get().setCreate_AT(product.getCreate_AT());
            exist.get().setExpire(product.getExpire());
            exist.get().setStatus(product.getStatus());
            exist.get().setQuantity(product.getQuantity());
            exist.get().setBuycampus_id(product.getBuycampus_id());
            exist.get().setSeller_id(product.getSeller_id());
            exist.get().setSellcampus_id(product.getSellcampus_id());
            exist.get().setCategory_id(product.getCategory_id());

        }else{

            throw new EntityNotFoundException("Not found: " + id);
        }

    }

    public void deleteProductById(int id){
         repo.deleteById(id);
    }
    public  Optional<Products> getProductById(int id){return repo.findById(id);}
}
