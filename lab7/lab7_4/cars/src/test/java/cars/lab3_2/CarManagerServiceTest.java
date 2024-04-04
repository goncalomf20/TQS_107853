package cars.lab3_2;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.reset;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cars.lab3_2.data.Car;
import cars.lab3_2.data.CarRepository;
import cars.lab3_2.service.CarManagerService;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() throws Exception {
        reset(carRepository);
    }

    @Test
    @DisplayName("Test save car")
    public void whenValidCar_thenCarShouldBeSaved() {
        Car car = new Car("Toyota", "Camry");
        Mockito.when(carRepository.save(car)).thenReturn(car);
        assertThat(carManagerService.save(car)).isEqualTo(car);
    }

    @Test
    @DisplayName("Test get all cars")
    public void whenGetAllCars_thenReturnAllCars() {
        Car car1 = new Car("Toyota", "Camry");
        Car car2 = new Car("Honda", "Civic");
        List<Car> allCars = Arrays.asList(car1, car2);
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        assertThat(carManagerService.getAllCars()).isEqualTo(allCars);
    }

    @Test
    @DisplayName("Test get car by id")
    public void whenGetCarDetails_thenReturnCar() {
        Car car = new Car("Toyota", "Camry");
        Mockito.when(carRepository.findByCarId(1L)).thenReturn(car);
        assertThat(carManagerService.getCarDetails(1L)).isEqualTo(java.util.Optional.of(car));
    }

    @Test
    @DisplayName("Test get car by id does not exist")
    public void whenGetCarDetails_thenReturnEmpty() {
        Mockito.when(carRepository.findByCarId(1L)).thenReturn(null);
        assertThat(carManagerService.getCarDetails(1L)).isEqualTo(java.util.Optional.empty());
    }

}
