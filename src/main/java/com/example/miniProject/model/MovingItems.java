package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MovingItems")
public class MovingItems {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "ProductId")
    private int productID;

    public MovingItems(int id, int productID) {
        this.id = id;
        this.productID = productID;
    }
    public MovingItems(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
