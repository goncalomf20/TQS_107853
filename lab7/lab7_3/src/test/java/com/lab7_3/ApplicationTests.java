package com.lab7_3;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

    @Container
    @Order(1)
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("goncalomf")
            .withPassword("password")
            .withDatabaseName("testcontainers");

    @Autowired
    private BookRepository bookRepository;

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
        // Given
        Book book = new Book();
        book.setTitle("Testcontainers");

        // When
        Book savedBook = bookRepository.save(book);

        // Then
        assertNotNull(savedBook.getId()); 
    }
}
