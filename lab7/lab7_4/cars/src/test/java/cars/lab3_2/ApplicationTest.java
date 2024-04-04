import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.lab7_3.model.Book;
import org.junit.jupiter.api.*;
import com.lab7_3.repository.BookRepository;

import cars.lab3_2.data.Car;
import cars.lab3_2.data.CarRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

    @LocalServerPort
    int randomServerPort;

    @Container
    @Order(1)
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("goncalomf")
            .withPassword("password")
            .withDatabaseName("testcars");

    @Autowired
    private CarRepository carRepository;

    // Set up database properties for Testcontainers
    @DynamicPropertySource
    @Order(2)
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @Order(3)
    void contextLoads() {

        Car audi, bmw;

        audi = new Car();
        audi.setMaker("AUDI");
        audi.setModel("A4");

        bmw = new Car();
        bmw.setMaker("BMW");
        bmw.setModel("E36");

        carRepository.save(bmw);
        carRepository.save(audi);

        // String endpoint = UriComponentsBuilder.newInstance()
        // .scheme("http")
        // .host("127.0.0.1")
        // .port(randomServerPort)
        // .pathSegment("api", "cars", String.valueOf( car1.getCarId()) )
        // .build()
        // .toUriString();




    }
}
