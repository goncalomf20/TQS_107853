package com.example.demo;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cars")
public class CarsController {


    private final CarService carService;   


    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/create" )
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carService.save( new Car("Audi","A4") );
        return new ResponseEntity<>(saved, status);
    }



}
