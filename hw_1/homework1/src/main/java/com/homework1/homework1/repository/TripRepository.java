package com.homework1.homework1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homework1.homework1.models.Trip;

public interface TripRepository extends JpaRepository<Trip, String>{

    Optional<Trip> findById(Long id);

	
}