package com.homework1.homework1.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.homework1.homework1.models.Ticket;
import com.homework1.homework1.repository.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketRepository ticketRepository;

    private Ticket t;
    private Ticket t2;
    private List<Ticket> tickets = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        t = new Ticket();
        t.setAddress("Avenida 25 de Abril, Alijó");
        t.setFirstName("Gonçalo");
        t.setLastName("Ferreira");
        t.setId_Number(123456789L);
        t.setId(1L);
        t.setZipCode("5070-011");
        t.setInsertToken("T-123456789");

        t2 = new Ticket();
        t2.setAddress("Rua da Laranja Doce, Castedo");
        t2.setFirstName("João");
        t2.setLastName("Milafres");
        t2.setId_Number(123456789L);
        t2.setZipCode("5070-022");
        t2.setId(2L);
        t2.setInsertToken("T-234567890");
    }

    @Test
    public void getAllTicketsTest() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(t);
        tickets.add(t2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Ticket> ticketsList = ticketService.getAllTickets();

        assertThat(ticketsList).hasSize(2)
                               .containsExactlyInAnyOrder(t, t2);

        verify(ticketRepository, times(1)).findAll();
    }


    @Test
    public void getAllTicketsButEmptyTest() {
        when(ticketRepository.findAll()).thenReturn(new ArrayList<>());
    
        List<Ticket> ticketsList = ticketService.getAllTickets();
    
        assertThat(ticketsList).isEmpty();
    
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void deleteAllTicketsTest() {
        ticketService.deleteAllTickets();
    
        verify(ticketRepository, times(1)).deleteAll();
    }

    @Test
    public void getTicketByIDTest() {
        ticketRepository.save(t);
        when(ticketRepository.findById(1L)).thenReturn(java.util.Optional.of(t));

        Ticket ticket = ticketService.getTicketByID(1L);

        assertThat(ticket).isEqualTo(t);
        verify(ticketRepository, times(1)).findById(1L);
    }
    
    @Test
    public void getTicketByTokenTest() {
        ticketRepository.save(t);
        when(ticketRepository.findByInsertToken("T-123456789")).thenReturn(java.util.Optional.of(t));

        Ticket ticket = ticketService.getTicketByToken("T-123456789");

        assertThat(ticket).isEqualTo(t);

        verify(ticketRepository, times(1)).findByInsertToken("T-123456789");
    }

    @Test
    public void getAllIdsTest() {
        tickets.add(t);
        tickets.add(t2);

        when(ticketRepository.findAll()).thenReturn(tickets);

        List<Long> ids = ticketService.getAllIds();

        assertThat(ids.size()).isEqualTo(2);
        assertThat(ids.get(0)).isEqualTo(t.getId());
        assertThat(ids.get(1)).isEqualTo(t2.getId());

        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void getAllIdsButEmptyTest() {
        when(ticketRepository.findAll()).thenReturn(new ArrayList<>());

        List<Long> ids = ticketService.getAllIds();

        assertThat(ids).isEmpty();
        
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void saveTicketAndDeleteTest() {
        ticketService.saveTicket(t);

        verify(ticketRepository, times(1)).save(t);

    }




}
