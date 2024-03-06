package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "application-integrationtest.properties")
public class C_CarRepositoryTest {
    
    @Autowired
    private CarRepository carRepo;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car toyota = new Car("Toyota", "Camry");
        Car opelCorsa = new Car("Opel", "Corsa");
        Car opelAstra = new Car("Opel", "Astra");

        testEntityManager.persist(toyota);
        testEntityManager.persist(opelCorsa);
        testEntityManager.persist(opelAstra);
        testEntityManager.flush();

        List<Car> allCars = carRepo.findAll();

        assertThat(allCars)
            .hasSize(3)
            .extracting(Car::getMaker)
            .containsOnly(toyota.getMaker(), opelCorsa.getMaker(), opelAstra.getMaker());
    }

    @Test
    void whenFindToyotaByMarker_thenReturnToyotaCar() {
        Car toyota = new Car("Toyota", "Camry");
        testEntityManager.persistAndFlush(toyota);

        Car foundCar = carRepo.findByMaker(toyota.getMaker());

        assertThat(foundCar).isEqualTo(toyota);
    }

    @Test
    void whenInvalidCarMarker_thenReturnNull() {
        Car nonExistentCar = carRepo.findByMaker("Does Not Exist");

        assertThat(nonExistentCar).isNull();
    }

    @Test
    void whenFindCarByExistingId_thenReturnCar() {
        Car toyota = new Car("Toyota", "Camry");
        testEntityManager.persistAndFlush(toyota);

        Car foundCar = carRepo.findById(toyota.getId()).orElse(null);

        assertThat(foundCar)
            .isNotNull()
            .extracting(Car::getMaker)
            .isEqualTo(toyota.getMaker());
    }

    @Test
    void whenInvalidId_thenReturnNull() {
        Car nonExistentCar = carRepo.findById(-111L).orElse(null);

        assertThat(nonExistentCar).isNull();
    }
}