package com.example.demo;

import jakarta.persistence.*;




@Entity
@Table(name = "cars")
public class Car {

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String maker;
    private String model;

    public Car(String maker, String model) {
        this.maker = maker;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
}
