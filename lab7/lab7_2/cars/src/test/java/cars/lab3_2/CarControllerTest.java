package cars.lab3_2;

import cars.lab3_2.controller.CarController;
import cars.lab3_2.data.Car;
import cars.lab3_2.service.CarManagerService;
import cars.lab3_2.JsonUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.beans.Transient;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    void setUp() throws Exception {
        reset(carManagerService);
    }

    @Test
    @DisplayName("Test get all cars")
    public void testGetAllCars() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry"));
        cars.add(new Car("Honda", "Civic"));

        when(carManagerService.getAllCars()).thenReturn(cars);

        mockMvc.perform(get("/api/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].maker", is("Toyota")))
                .andExpect(jsonPath("$[0].model", is("Camry")))
                .andExpect(jsonPath("$[1].maker", is("Honda")))
                .andExpect(jsonPath("$[1].model", is("Civic")));

        verify(carManagerService, times(1)).getAllCars();
        System.out.println("Test [get all cars] passed!");
    }

    @Test
    @DisplayName("Test get car by id")
    public void testGetCarById() throws Exception {
        Car car = new Car("Toyota", "Camry");
        car.setId(1L);

        when(carManagerService.getCarDetails(1L)).thenReturn(java.util.Optional.of(car));

        mockMvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.maker", is("Toyota")))
                .andExpect(jsonPath("$.model", is("Camry")));

        verify(carManagerService, times(1)).getCarDetails(1L);
        System.out.println("Test [get car by id] passed!");
    }

    @Test
    @DisplayName("Test create car")
    public void testCreateCar() throws Exception {
        Car car = new Car("Ford", "Transit");

        Mockito.when(carManagerService.save(Mockito.any(Car.class))).thenReturn(car);

        mockMvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("Ford")))
                .andExpect(jsonPath("$.model", is("Transit")));

        Mockito.verify(carManagerService, Mockito.times(1)).save(Mockito.any(Car.class));

        System.out.println("Test [create car] passed!");
    }

    @Test
    @DisplayName("Test create car with null maker")
    public void testCreateCarWithNullMaker() throws Exception {
        Car car = new Car(null, "Transit");

        Mockito.when(carManagerService.save(Mockito.any(Car.class))).thenReturn(car);

        mockMvc.perform(
                post("/api/cars").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker").doesNotExist())
                .andExpect(jsonPath("$.model", is("Transit")));

        Mockito.verify(carManagerService, Mockito.times(1)).save(Mockito.any(Car.class));

        System.out.println("Test [create car with null maker] passed!");
    }

    @Test
    @DisplayName("Test get car by id not found")
    public void testGetCarByIdNotFound() throws Exception {
        when(carManagerService.getCarDetails(1L)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/api/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(carManagerService, times(1)).getCarDetails(1L);
        System.out.println("Test [get car by id not found] passed!");
    }
}
