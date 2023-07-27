package com.example.miniProject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "ProductMovings")
//@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
public class ProductMovings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MovingId")
    private int MovingId;
    @Column(name= "MovingDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp MovingDate;
    @Column(name = "ArrivalDate")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp ArrivalDate;
    @Column(name = "FromLocation")
    private int FromLocation;
    @Column(name = "ToLocation")
    private int ToLocation;
    @Column(name = "Status")
    private String status;
    @Column(name = "ShipperId",nullable = true)
    private Integer ShipperId;

    public ProductMovings() {
    }

    public ProductMovings(int movingId, Timestamp movingDate, Timestamp arrivalDate, int fromLocation, int toLocation, String status, Integer shipperId) {
        this.MovingId = movingId;
        this.MovingDate = movingDate;
        this.ArrivalDate = arrivalDate;
        this.FromLocation = fromLocation;
        this.ToLocation = toLocation;
        this.status = status;
        this.ShipperId = shipperId;
    }

    public int getMovingId() {
        return MovingId;
    }

    public void setMovingId(int movingId) {
        MovingId = movingId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("MovingDate")
    public Timestamp getMovingDate() {
        return MovingDate;
    }

    public void setMovingDate(Timestamp movingDate) {
        MovingDate = movingDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("ArrivalDate")
    public Timestamp getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(Timestamp arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public int getFromLocation() {
        return FromLocation;
    }

    public void setFromLocation(int fromLocation) {
        FromLocation = fromLocation;
    }

    public int getToLocation() {
        return ToLocation;
    }

    public void setToLocation(int toLocation) {
        ToLocation = toLocation;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShipperId() {
        return ShipperId;
    }

    public void setShipperId(Integer shipperId) {
        ShipperId = shipperId;
    }
}
