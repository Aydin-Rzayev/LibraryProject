package com.OIBSIP.LibrarySystem.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OIBSIP.LibrarySystem.Models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    
}
