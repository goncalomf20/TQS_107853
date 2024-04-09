package com.homework1.homework1.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.homework1.homework1.models.Ticket;
import com.homework1.homework1.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    

    @GetMapping("/getall")
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/getTicketToken/{token}")
    public Ticket getTicketbyToken(@PathVariable String token) {
        return ticketService.getTicketByToken(token);
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return ticketService.getTicketByID(id);
    }

    @GetMapping("/getAllIds")
    public List<Long> getAllIds() {
        return ticketService.getAllIds();
    }


    @PostMapping("/add")
    public void createTicket(@RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
    }
    

    @DeleteMapping("/deleteAll")
    public String deleteAllTickets() {
        ticketService.deleteAllTickets();
        return "All tickets have been deleted successfully.";
    }

}
