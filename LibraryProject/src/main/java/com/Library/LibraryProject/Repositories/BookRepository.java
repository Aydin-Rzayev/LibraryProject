package com.Library.LibraryProject.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Library.LibraryProject.Enums.Statuses;
import com.Library.LibraryProject.Models.Book;


public interface BookRepository extends JpaRepository<Book, Integer> {
    public Optional<List<Book>> findByBookName(String bookName);
    public Optional<List<Book>> findByBookStatuse(Statuses statuse);
    public Optional<List<Book>> findByBookAuthor(String author);
    public Optional<Book> updateBook(Integer id, Book book);
}
