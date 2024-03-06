package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.Car;
import com.example.demo.CarService;
import com.example.demo.CarsController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarsController.class)
public class A_CarControllerTest {
    
    @Autowired
    private MockMvc mvc;    //entry point to the web framework

    @MockBean
    private CarService service;


    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenPostCar_thenCreateCar( ) throws Exception {
        Car Audi = new Car("Audi", "A4");

        when( service.save(Mockito.any()) ).thenReturn(Audi);

        mvc.perform(
                post("/api/cars/create").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(Audi)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Audi")));
        
                

        verify(service, times(1)).save(Mockito.any());

    }

}
