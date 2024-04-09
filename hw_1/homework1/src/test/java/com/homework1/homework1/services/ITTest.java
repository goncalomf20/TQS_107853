// package com.homework1.homework1.services;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.Assert.assertThat;
// import static org.junit.jupiter.api.Assertions.assertAll;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Nested;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.openqa.selenium.InvalidArgumentException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.web.client.TestRestTemplate;
// import org.springframework.http.ResponseEntity;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.homework1.homework1.controller.TicketController;
// import com.homework1.homework1.controller.TripController;
// import com.homework1.homework1.models.Ticket;
// import com.homework1.homework1.models.Trip;
// import com.homework1.homework1.repository.TicketRepository;
// import com.homework1.homework1.repository.TripRepository;

// import io.cucumber.java.Before;
// import org.junit.jupiter.api.Assertions;

// @WebMvcTest({TicketController.class, TripController.class})
// @ExtendWith(SpringExtension.class)
// public class ITTest {

//     @Autowired
//     private TicketRepository ticketRepository;

//     @Autowired
//     private TestRestTemplate testrestTemplate;

//     @Autowired
//     private TripRepository tripRepository;

//     @BeforeEach
//     void setUp() {
//         Trip t = new Trip();
//         t.setAvailableSeats(10);
//         t.setDeparture("Porto");
//         t.setDestination("London");
//         tripRepository.save(t);
        

//         Ticket ticket = new Ticket();
//         ticket.setAddress("Rua da Laranja Doce, Castedo");
//         ticket.setFirstName("João");    
//         ticket.setLastName("Milafres");
//         ticket.setId_Number(123456789L);
//         ticket.setZipCode("5070-022");
//         ticket.setTrip(t.getId());
//         ticketRepository.save(ticket);
//     }

//     @Test
//     void getAllTripsTest() {
//         Trip[] trips = this.testrestTemplate.getForObject("/trip/getall", Trip[].class);
//         assertAll(
//             () -> assertNotNull(trips),
//             () -> assertThat(trips.length).isGreaterThan(0)
//         );
//     }

//     @Test
//     void getAllTicketsTest() {
//         Ticket[] tickets = this.testrestTemplate.getForObject("/ticket/getall", Ticket[].class);
//         assertAll(
//             () -> assertNotNull(tickets),
//             () -> assertThat(tickets.length).isGreaterThan(0)
//         );
//     }

//     @Test
//     void getTicketByTokenTest() {
//         Ticket ticket = this.testrestTemplate.getForObject("/ticket/getTicketToken/T-123456789", Ticket.class);
//         assertAll(
//             () -> assertNotNull(ticket),
//             () -> assertThat(ticket.getFirstName()).isEqualTo("João")
//         );
//     }

//     @Test
//     void getTripByIdTest() {
//         Trip trip = this.testrestTemplate.getForObject("/trip/1", Trip.class);
//         assertAll(
//             () -> assertNotNull(trip),
//             () -> assertThat(trip.getDeparture()).isEqualTo("Porto")
//         );
//     }

//     @Test
//     void getTicketByIdTest() {
//         Ticket ticket = this.testrestTemplate.getForObject("/ticket/1", Ticket.class);
//         assertAll(
//             () -> assertNotNull(ticket),
//             () -> assertThat(ticket.getFirstName()).isEqualTo("João")
//         );
//     }

//     @Test
//     void getAllIdsTest() {
//         Long[] ids = this.testrestTemplate.getForObject("/ticket/getAllIds", Long[].class);
//         assertAll(
//             () -> assertNotNull(ids),
//             () -> assertThat(ids.length).isGreaterThan(0)
//         );
//     }

// }
