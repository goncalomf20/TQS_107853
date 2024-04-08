package com.homework1.homework1.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.homework1.homework1.models.Trip;
import com.homework1.homework1.repository.TripRepository;

public class TripServiceTest {

    @InjectMocks
    private TripService tripService;

    @Mock
    private TripRepository tripRepository;

    private Trip t;
    private Trip t2;
    private List<Trip> trips = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // DOCUMENTATION WAY TO CORRECT NULL POINTER EXCEPTION

        t = new Trip();
        t.setDeparture("Porto");
        t.setDestination("London");
        t.setDepartureDate(new Date());
        t.setTripDuration(3);
        t.setPrice(140);
        t.setAvailableSeats(10);

        t2 = new Trip();
        t2.setDeparture("Madrid");
        t2.setDestination("Barcelona");
        t2.setDepartureDate(new Date());
        t2.setTripDuration(1);
        t2.setPrice(40);
        t2.setAvailableSeats(4);
    }

    @Test
    public void addTripsTest() {
        tripService.saveTrip(t);
        tripService.saveTrip(t2);

        verify(tripRepository, times(1)).save(t);
        verify(tripRepository, times(1)).save(t2);
    }

    @Test
    public void deleteTripsTest() {
        tripService.deleteTrip(t);
        tripService.deleteTrip(t2);

        verify(tripRepository, times(1)).delete(t);
        verify(tripRepository, times(1)).delete(t2);
    }

    @Test
    public void deleteAllTripsTest() {
        tripService.deleteAllTrips();
    
        verify(tripRepository, times(1)).deleteAll();
    }

    @Test
    public void getAllTripsTest() {
        trips.add(t);
        trips.add(t2);

        when(tripRepository.findAll()).thenReturn(trips);

        List<Trip> t_fromservice = tripService.getAllTrips();

        assertThat(trips).hasSize(2)
                        .containsExactlyInAnyOrder(t, t2);
        assertThat(t_fromservice).hasSize(2)
                        .containsExactlyInAnyOrder(t, t2);

        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void getTripsWhenEmpty() {
        when(tripRepository.findAll()).thenReturn(trips);

        List<Trip> t_fromservice = tripService.getAllTrips();

        assertThat(trips).isEmpty();
        assertThat(t_fromservice).isEmpty();

        verify(tripRepository, times(1)).findAll();
    }

    @Test
    public void getTripByIdTest() {
        tripRepository.save(t);
        when(tripRepository.findById(1L)).thenReturn(java.util.Optional.of(t));

        Trip trip = tripService.getTripById(1L);

        assertThat(trip).isEqualTo(t);
        verify(tripRepository, times(1)).findById(1L);
    }

}
