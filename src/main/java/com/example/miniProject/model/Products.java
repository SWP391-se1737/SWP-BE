package com.example.miniProject.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name= "Products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name = "Image")
    private String image;
    @Column(name = "Description")
    private String description;
    @Column(name = "Price")
    private float price;
    @Column(name = "CreateAt")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_AT;
    @Column(name = "Expire")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expire;
    @Column(name ="Quantity")
    private int quantity;
    @Column(name = "SellerId")
    private int seller_id;
    @Column(name = "BuyCampusId")
    private int buycampus_id;
    @Column(name = "SellCampusId")
    private int sellcampus_id;
    @Column(name = "CategoryID")
    private int category_id;
    @Column(name = "Status")
    private String status;

    public Products() {
    }

    public Products(int id, String name, String image, String description, float price, Date create_AT, Date expire,String status, int quantity, int seller_id, int buycampus_id, int sellcampus_id, int category_id) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.create_AT = new Date();
        this.expire = expire;
        this.status = status;
        this.quantity = quantity;
        this.seller_id = seller_id;
        this.buycampus_id = buycampus_id;
        this.sellcampus_id = sellcampus_id;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreate_AT() {
        return create_AT;
    }

    public void setCreate_AT(Date create_AT) {
        this.create_AT = new Date();
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getBuycampus_id() {
        return buycampus_id;
    }

    public void setBuycampus_id(int buycampus_id) {
        this.buycampus_id = buycampus_id;
    }

    public int getSellcampus_id() {
        return sellcampus_id;
    }

    public void setSellcampus_id(int sellcampus_id) {
        this.sellcampus_id = sellcampus_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
