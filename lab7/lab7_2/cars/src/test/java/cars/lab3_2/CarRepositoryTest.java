package cars.lab3_2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import cars.lab3_2.data.Car;
import cars.lab3_2.data.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void whenFindById_thenReturnCar() {
        Car camry = new Car("Toyota", "Camry");
        entityManager.persist(camry);
        entityManager.flush();

        Car found = carRepository.findByCarId(camry.getId());

        assertThat(found.getMaker())
                .isEqualTo(camry.getMaker());

        System.out.println("Test [whenFindById_thenReturnCar] passed");
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findByCarId(-11L);

        assertThat(fromDb).isNull();

        System.out.println("Test [whenInvalidId_thenReturnNull] passed");
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car camry = new Car("Toyota", "Camry");
        Car civic = new Car("Honda", "Civic");
        Car corolla = new Car("Toyota", "Corolla");

        entityManager.persist(camry);
        entityManager.persist(civic);
        entityManager.persist(corolla);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).contains(camry, civic, corolla).extracting(Car::getModel)
                .containsOnly(camry.getModel(), civic.getModel(), corolla.getModel());

        System.out.println("Test [givenSetOfCars_whenFindAll_thenReturnAllCars] passed");
    }

}
