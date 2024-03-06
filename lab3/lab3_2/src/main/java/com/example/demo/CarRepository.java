package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByCarId(Long id);
    List<Car> findAll();
    Car findByMaker(String maker);
}
