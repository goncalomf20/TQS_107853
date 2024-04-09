package com.homework1.homework1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homework1.homework1.models.Ticket;
import com.homework1.homework1.repository.TicketRepository;

import java.util.List;

import java.util.ArrayList;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketByID(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
    }

    public Ticket getTicketByToken(String token) {
        return ticketRepository.findByInsertToken(token)
                .orElseThrow(() -> new RuntimeException("Ticket not found with token: " + token));
    }

    public void saveTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void deleteAllTickets() {
        ticketRepository.deleteAll();
    }

    public List<Long> getAllIds() {
        List<Ticket> tickets = ticketRepository.findAll();
        List<Long> ids = new ArrayList<>();
        for (Ticket t : tickets) {
            ids.add(t.getId());
        }
        return ids;
    }

    
}
