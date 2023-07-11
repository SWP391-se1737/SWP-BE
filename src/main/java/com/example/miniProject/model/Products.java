package com.example.miniProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAT;
    @Column(name = "Expire")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expire;
    @Column(name ="Quantity")
    private int quantity;
    @Column(name = "SellerId")
    private int seller_id;

    @Column(name = "SellCampusId")
    private int sellcampusid;
    @Column(name = "CategoryID")
    private int categoryid;
    @Column(name = "Status")
    private String status;

    public Products() {
    }

    public Products(int id, String name, String image, String description, float price, LocalDateTime createAT, LocalDateTime expire,String status, int quantity, int seller_id,  int sellcampusid, int categoryid) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createAT = createAT;
        this.expire = expire;
        this.status = status;
        this.quantity = quantity;
        this.seller_id = seller_id;
        this.sellcampusid = sellcampusid;
        this.categoryid = categoryid;
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

    public LocalDateTime getCreateAT() {
        return createAT;
    }

    public void setCreateAT(LocalDateTime create_AT) {
        this.createAT = createAT;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
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

    public int getSellcampusid() {
        return sellcampusid;
    }

    public void setSellcampusid(int sellcampusid) {
        this.sellcampusid = sellcampusid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
