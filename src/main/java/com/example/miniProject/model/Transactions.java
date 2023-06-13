package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;
    @Column(name ="Order_id")
    private int order_id;
    @Column(name = "Wallet_id")
    private int wallet_id;
    @Column(name = "Amount")
    private float amount;
    @Column(name = "Status")
    private boolean status;
    @Column(name = "Exchange_id")
    private int exchange_id;
    @Column(name = "Product_id")
    private int product_id;

    public Transactions() {
    }

    public Transactions(int id, int order_id, int wallet_id, float amount, boolean status, int exchange_id, int product_id) {
        this.id = id;
        this.order_id = order_id;
        this.wallet_id = wallet_id;
        this.amount = amount;
        this.status = status;
        this.exchange_id = exchange_id;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(int wallet_id) {
        this.wallet_id = wallet_id;
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

    public int getExchange_id() {
        return exchange_id;
    }

    public void setExchange_id(int exchange_id) {
        this.exchange_id = exchange_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}
