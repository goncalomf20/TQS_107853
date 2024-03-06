package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CarService {

    public Car save(Car car);
    public List<Car> getAllCars();
    public Car getCarDetails(Long id);
    
}
