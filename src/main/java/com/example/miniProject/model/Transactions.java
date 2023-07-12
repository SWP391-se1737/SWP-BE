package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private int id;

    @Column(name = "Order_id", nullable = true)
    private Integer order_id;
    @Column(name = "Wallet_user")
    private int wallet_user;
    @Column(name = "Amount")
    private float amount;
    @Column(name = "Status")
    private boolean status;


    @Column(name = "Product_id", nullable = true)
    private Integer product_id;

    @Column(name = "deposit_id", nullable = true)
    private Integer deposit_id;
    @Column(name = "Description")
    private String description;

    public Transactions() {
    }

    public Transactions(int id, Integer order_id, int wallet_user, float amount, boolean status, Integer product_id, Integer deposit_id, String description) {
        this.id = id;
        this.order_id = order_id;
        this.wallet_user = wallet_user;
        this.amount = amount;
        this.status = status;
        this.product_id = product_id;
        this.deposit_id = deposit_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public int getWallet_user() {
        return wallet_user;
    }

    public void setWallet_user(int wallet_user) {
        this.wallet_user = wallet_user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getDeposit_id() {
        return deposit_id;
    }

    public void setDeposit_id(Integer deposit_id) {
        this.deposit_id = deposit_id;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
