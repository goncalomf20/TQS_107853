package com.homework1.homework1.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.homework1.homework1.models.Ticket;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DataJpaTest
public class TicketRepositoryTest {
    
    @Autowired
    private TicketRepository ticketRepository;

    private Long ticketId;

    @BeforeEach
    public void setUp() {
        Ticket ticket = new Ticket();
        ticket.setAddress("Avenida 25 de Abril, Alijó");
        ticket.setFirstName("Gonçalo");
        ticket.setLastName("Ferreira");
        ticket.setId_Number(123456789L);
        ticket.setZipCode("5070-011");
        ticket.setTrip(1L);
        ticket.setInsertToken("T-123456789");
        Ticket savedTicket = ticketRepository.save(ticket);
        ticketId = savedTicket.getId();
    }
    

    @Test
    public void testFindById() {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        assertEquals("Avenida 25 de Abril, Alijó", ticket.getAddress());
        assertEquals("Gonçalo", ticket.getFirstName());
        assertEquals("Ferreira", ticket.getLastName());
        assertEquals(123456789L, ticket.getId_Number());
        assertEquals("5070-011", ticket.getZipCode());
        assertEquals("T-123456789", ticket.getInsertToken());
    }

    @Test
    public void testDeleteAll() {
        assertEquals(1, ticketRepository.findAll().size());
        ticketRepository.deleteAll();
        assertThat(ticketRepository.findAll().isEmpty());
    }

    @Test
    public void testDeleteById() {
        assertEquals(1, ticketRepository.findAll().size());
        ticketRepository.delete(ticketRepository.findById(ticketId).get());
        assertThat(ticketRepository.findAll().isEmpty());
    }

    @Test
    public void testFindByInsertToken() {
        Ticket ticket = ticketRepository.findByInsertToken("T-123456789").get();
        assertEquals("Avenida 25 de Abril, Alijó", ticket.getAddress());
        assertEquals("Gonçalo", ticket.getFirstName());
        assertEquals("Ferreira", ticket.getLastName());
        assertEquals(123456789L, ticket.getId_Number());
        assertEquals("5070-011", ticket.getZipCode());
        assertEquals(ticketId, ticket.getId());
    }

    @Test
    public void testFindChangeAndSave(){
        Ticket t = ticketRepository.findById(ticketId).get();
        t.setFirstName("João");
        ticketRepository.save(t);

        Ticket t_aftersave = ticketRepository.findById(ticketId).get();

        assertEquals(t_aftersave.getFirstName(), "João");
    }


}
