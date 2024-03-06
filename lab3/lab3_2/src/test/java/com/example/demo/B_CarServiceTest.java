package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class B_CarServiceTest {

    @MockBean
    private CarRepository repository; // Mock the CarRepository bean

    @Autowired
    private CarService service; // Inject the CarService bean

    @Test
    void whenGetAllCars_thenReturnAllCars() {
        // Given
        Car car1 = new Car("Audi", "A4");
        Car car2 = new Car("BMW", "X5");

        // Mock the behavior of CarRepository
        when(repository.findAll()).thenReturn(Arrays.asList(car1, car2));

        // When
        List<Car> result = service.getAllCars();

        // Then
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getMaker(), "Audi");
        assertEquals(result.get(1).getMaker(), "BMW");

        // Verify that CarRepository.findAll() is called once
        verify(repository, times(1)).findAll();
    }
}
