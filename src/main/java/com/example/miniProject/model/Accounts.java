package com.example.miniProject.model;

import jakarta.persistence.*;


@Entity
@Table(name = "Accounts")
public class Accounts {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private int Id;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Phone")
    private String Phone;
    @Column(name = "Role")
    private int Role;
    @Column(name = "Status")
    private boolean Status;

    public Accounts(int id, String email, String phone, int role, boolean status) {
        Id = id;
        Email = email;
        Phone = phone;
        Role = role;
        Status = status;
    }
    public Accounts(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getRole() {
        return Role;
    }

    public void setRole(int role) {
        Role = role;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
