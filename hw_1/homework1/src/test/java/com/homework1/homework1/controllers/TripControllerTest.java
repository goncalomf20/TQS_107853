package com.homework1.homework1.controllers;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.homework1.homework1.controller.TripController;
import com.homework1.homework1.models.Trip;
import com.homework1.homework1.services.TripService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TripController.class)
public class TripControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    private Trip t;
    private Trip t2;
    
    @BeforeEach
    public void setUp() {
        t = new Trip();
        t.setId(1L);
        t.setDeparture("Porto");
        t.setDestination("London");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2021);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date departureDate = calendar.getTime();
        //necessary to put a date in the past, just in case of running the test in the future
        t.setDepartureDate(departureDate);
        t.setTripDuration(3);
        t.setPrice(140);
        t.setAvailableSeats(10);

        t2 = new Trip();
        t2.setId(2L);
        t2.setDeparture("Madrid");
        t2.setDestination("Barcelona");
        t2.setDepartureDate(new Date());
        t2.setTripDuration(1);
        t2.setPrice(40);
        t2.setAvailableSeats(4);
        
        when(tripService.getAllTrips()).thenReturn(List.of(t, t2));
        when(tripService.getTripById(1L)).thenReturn(t);
    }

    @Test
    public void getAllTripsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trip/getall")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].departure").value("Porto"))
                .andExpect(jsonPath("$[1].destination").value("Barcelona"))
                .andExpect(jsonPath("$[0].availableSeats").value(10))
                .andExpect(jsonPath("$[1].price").value(40));
    }

    @Test
    public void getTripById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trip/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departure").value("Porto"))
                .andExpect(jsonPath("$.destination").value("London"))
                .andExpect(jsonPath("$.availableSeats").value(10))
                .andExpect(jsonPath("$.price").value(140));
    }

    @Test
    public void saveTrip() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trip/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"departure\":\"Porto\",\"destination\":\"London\",\"departureDate\":\"2021-09-01\",\"tripDuration\":3,\"price\":140,\"availableSeats\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTripTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/trip/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"departure\":\"Porto\",\"destination\":\"London\",\"departureDate\":\"2021-09-01\",\"tripDuration\":3,\"price\":140,\"availableSeats\":10}"))
                .andExpect(status().isOk());
    }

}   
