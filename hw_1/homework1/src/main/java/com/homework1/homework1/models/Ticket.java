package com.homework1.homework1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "insert_token" ,nullable = false)
    private String insertToken;

    @Column(nullable = false)
    private Long id_Number;

    @Column(nullable = false)
    private String address;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = true)
    private Long trip;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;




    // Getters and setters

    
    public Ticket() {
    // Empty constructor
    }
    
    // Getters
    public String getInsertToken() {
        return insertToken;
    }
    public String getAddress() {
        return address;
    }
    public String getFirstName() {
        return firstName;
    }
    public Long getId() {
        return id;
    }
    public Long getId_Number() {
        return id_Number;
    }
    public Long getTrip() {
        return trip;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getLastName() {
        return lastName;
    }
    // Setters
    public void setInsertToken(String insertToken) {
        this.insertToken = insertToken;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setId_Number(Long id_Number) {
        this.id_Number = id_Number;
    }
    public void setTrip(Long trip) {
        this.trip = trip;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
