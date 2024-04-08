package com.homework1.homework1.controllers;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.homework1.homework1.controller.TicketController;
import com.homework1.homework1.models.Ticket;
import com.homework1.homework1.services.TicketService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    private Ticket t;
    private Ticket t2;
    
    @BeforeEach
    void setUp() {
        t = new Ticket();
        t.setAddress("Avenida 25 de Abril, Alijó");
        t.setFirstName("Gonçalo");
        t.setId(1L);
        t.setLastName("Ferreira");
        t.setId_Number(123456789L);
        t.setZipCode("5070-011");
        t.setInsertToken("T-123456789");

        t2 = new Ticket();
        t2.setAddress("Rua da Laranja Doce, Castedo");
        t2.setFirstName("João");
        t.setId(2L);
        t2.setLastName("Milafres");
        t2.setId_Number(123456789L);
        t2.setZipCode("5070-022");
        t2.setInsertToken("T-234567890");

        when(ticketService.getAllTickets()).thenReturn(List.of(t, t2));
        when(ticketService.getTicketByToken("T-123456789")).thenReturn(t);
        when(ticketService.getTicketByID(1L)).thenReturn(t);
        when(ticketService.getAllIds()).thenReturn(List.of(1L,2L));
    }

    @Test
    void getAllTicketsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/getall").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address").value("Avenida 25 de Abril, Alijó"))
                .andExpect(jsonPath("$[0].firstName").value("Gonçalo"))
                .andExpect(jsonPath("$[0].lastName").value("Ferreira"))
                .andExpect(jsonPath("$[0].id_Number").value(123456789L))
                .andExpect(jsonPath("$[0].zipCode").value("5070-011"))
                .andExpect(jsonPath("$[0].insertToken").value("T-123456789"))
                .andExpect(jsonPath("$[1].address").value("Rua da Laranja Doce, Castedo"))
                .andExpect(jsonPath("$[1].firstName").value("João"))
                .andExpect(jsonPath("$[1].lastName").value("Milafres"))
                .andExpect(jsonPath("$[1].id_Number").value(123456789L))
                .andExpect(jsonPath("$[1].zipCode").value("5070-022"))
                .andExpect(jsonPath("$[1].insertToken").value("T-234567890"));
    }

    @Test
    void getTicketByTokenTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/getTicketToken/T-123456789").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Avenida 25 de Abril, Alijó"))
                .andExpect(jsonPath("$.firstName").value("Gonçalo"))
                .andExpect(jsonPath("$.lastName").value("Ferreira"))
                .andExpect(jsonPath("$.id_Number").value(123456789L))
                .andExpect(jsonPath("$.zipCode").value("5070-011"))
                .andExpect(jsonPath("$.insertToken").value("T-123456789"));
    }

    @Test
    void getTicketByIDTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("Avenida 25 de Abril, Alijó"))
                .andExpect(jsonPath("$.firstName").value("Gonçalo"))
                .andExpect(jsonPath("$.lastName").value("Ferreira"))
                .andExpect(jsonPath("$.id_Number").value(123456789L))
                .andExpect(jsonPath("$.zipCode").value("5070-011"))
                .andExpect(jsonPath("$.insertToken").value("T-123456789"));
    }

    @Test
    void getAllIdsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ticket/getAllIds").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value(1L))
                .andExpect(jsonPath("$[1]").value(2L));
    }

    @Test
    void saveTicketTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/ticket/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"address\":\"Rua da Laranja Doce, Castedo\",\"firstName\":\"João\",\"lastName\":\"Milafres\",\"id_Number\":123456789,\"zipCode\":\"5070-022\",\"insertToken\":\"T-234567890\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllTicketsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/ticket/deleteAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    

}
