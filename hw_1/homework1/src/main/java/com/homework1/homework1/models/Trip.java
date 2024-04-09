package com.homework1.homework1.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trip")
public class Trip {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "departure")
    private String departure;

    @Column(nullable = false, name = "destination")
    private String destination;

    @Column(nullable = false, name = "departure_date")
    private Date departureDate;

    @Column(nullable = false, name = "trip_duration")
    private int tripDuration;

    @Column(nullable = false, name = "price")
    private int price;

    @Column(nullable = false, name = "available_seats")
    private int availableSeats;

    // Constructor
    public Trip() {
        //empty constructor
    }

    // Getters
    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getTripDuration() {
        return tripDuration;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public Long getId() {
        return id;
    }

    // Setters
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setTripDuration(int tripDuration) {
        this.tripDuration = tripDuration;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAvailableSeats(int availableSeats) {
        if (availableSeats < 0) throw new IllegalArgumentException("Available seats cannot be negative");
        this.availableSeats = availableSeats;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
