package com.homework1.homework1.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.homework1.homework1.models.Trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
public class TripRepositoryTest{
    
    @Autowired
    private TripRepository tripRepository;

    private Long tripId;

    @BeforeEach
    public void setUp() {
        Trip trip = new Trip();
        trip.setDeparture("Porto");
        trip.setDestination("London");
        trip.setDepartureDate(new Date());
        trip.setTripDuration(3);
        trip.setPrice(140);
        trip.setAvailableSeats(10);
        tripRepository.save(trip);
        tripId = trip.getId();        
    }

    @Test
    public void testFindById() {
        Trip trip = tripRepository.findById(tripId).get();
        assertEquals("Porto", trip.getDeparture());
        assertEquals("London", trip.getDestination());
        assertEquals(3, trip.getTripDuration());
        assertEquals(140, trip.getPrice());
        assertEquals(10, trip.getAvailableSeats());
    }

    @Test
    public void testDeleteById() {
        assertEquals(1, tripRepository.findAll().size());
        tripRepository.deleteAll();
        assertThat(tripRepository.findAll().isEmpty());
    }


    @Test
    public void testFindChangeAndSave(){
        Trip t = tripRepository.findById(tripId).get();
        t.setAvailableSeats(t.getAvailableSeats() - 1);
        Integer availableSeats = t.getAvailableSeats();
        tripRepository.save(t);

        Trip t_aftersave = tripRepository.findById(tripId).get();

        assertEquals(t_aftersave.getAvailableSeats(), availableSeats);
    }
    @Test
    void testCreateTicketWith0SeatsAvailable(){
        Trip trip = new Trip();
        trip.setDeparture("Porto");
        trip.setDestination("London");
        trip.setDepartureDate(new Date());
        trip.setTripDuration(3);
        trip.setPrice(140);
        trip.setAvailableSeats(0);

        try {
            tripRepository.save(trip);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
