package com.homework1.homework1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.homework1.homework1.models.Trip;
import com.homework1.homework1.services.TripService;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/getall")
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }

    @PostMapping("/add")
    public void createTrip(@RequestBody Trip trip) {
        tripService.saveTrip(trip);
    }

    @DeleteMapping("/delete")
    public void deleteTrip(@RequestBody Trip trip) {
        tripService.deleteTrip(trip);
    }

}
