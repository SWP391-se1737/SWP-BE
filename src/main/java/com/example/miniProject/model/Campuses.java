package com.example.miniProject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Campuses")
public class Campuses {
    @jakarta.persistence.Id
    @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Id")
    private int Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Address")
    private String Address;
    @Column(name = "Longitude")
    private String Longitude;
    @Column(name = "Latitude")
    private String Latitude;
    @Column(name = "status")
    private boolean status;

    public Campuses(int id, String name, String address, String longitude, String latitude, boolean status) {
        Id = id;
        Name = name;
        Address = address;
        Longitude = longitude;
        Latitude = latitude;
        this.status = status;
    }
    public Campuses(){

    }

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
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
