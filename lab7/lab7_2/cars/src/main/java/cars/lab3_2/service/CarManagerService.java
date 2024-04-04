package cars.lab3_2.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import cars.lab3_2.data.Car;
import cars.lab3_2.data.CarRepository;

@Service
public class CarManagerService {
    private CarRepository carRepository;

    public CarManagerService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId) {
        Car car = carRepository.findByCarId(carId);
        return Optional.ofNullable(car);
    }

}
