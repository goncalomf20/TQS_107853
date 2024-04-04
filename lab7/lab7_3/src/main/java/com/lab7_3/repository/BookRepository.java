package com.lab7_3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lab7_3.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
}
