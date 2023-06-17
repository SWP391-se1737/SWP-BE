package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Deposits")
public class Deposits {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "currency_from")
    private float currency_from;
    @Column(name = "currency_to")
    private float currency_to;
    @Column(name = "userID")
    private int userID;
    @Column(name = "exchangeProvider")
    private String exchangeProvider;

    public Deposits(int id, float currency_from, float currency_to, int userID, String exchangeProvider) {
        this.id = id;
        this.currency_from = currency_from;
        this.currency_to = currency_to;
        this.userID = userID;
        this.exchangeProvider = exchangeProvider;
    }

    public Deposits(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCurrency_from() {
        return currency_from;
    }

    public void setCurrency_from(float currency_from) {
        this.currency_from = currency_from;
    }

    public float getCurrency_to() {
        return currency_to;
    }

    public void setCurrency_to(float currency_to) {
        this.currency_to = currency_to;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getExchangeProvider() {
        return exchangeProvider;
    }

    public void setExchangeProvider(String exchangeProvider) {
        this.exchangeProvider = exchangeProvider;
    }
}
