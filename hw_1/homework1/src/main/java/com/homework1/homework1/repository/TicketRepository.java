package com.homework1.homework1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homework1.homework1.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String>{

    Optional<Ticket> findById(Long id);
    Optional<Ticket> findByInsertToken(String insertToken);
    void delete(Ticket ticket);
}