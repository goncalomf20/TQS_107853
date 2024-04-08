package com.homework1.homework1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homework1.homework1.models.Trip;
import com.homework1.homework1.repository.TripRepository;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
    }

    public void saveTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public void deleteTrip(Trip trip) {
        tripRepository.delete(trip);
    }

    public void deleteAllTrips() {
        tripRepository.deleteAll();
    }
    
    
}
