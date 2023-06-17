package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Categories")
public class Categories {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private int Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Status")
    private boolean Status;

    public Categories(int id, String name, boolean status) {
        Id = id;
        this.Name = name;
        Status = status;
    }
    public Categories(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
