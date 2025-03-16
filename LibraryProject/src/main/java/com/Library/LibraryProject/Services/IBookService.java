package com.Library.LibraryProject.Services;

import java.util.List;
import java.util.Optional;

import com.Library.LibraryProject.DTOs.BookDTO;
import com.Library.LibraryProject.DTOs.BookIU;
import com.Library.LibraryProject.Enums.Statuses;



public interface IBookService {

    Optional<List<BookDTO>> findAllBooks();

    Optional<BookDTO> findBookById(Integer id);

    Optional<List<BookDTO>> findBooksByName(String name);

    Optional<List<BookDTO>> findBooksByStatuse(Statuses statuse);

    Optional<List<BookDTO>> findBooksByAuthor(String author);

    boolean existsBook(BookIU bookIU);

    Optional<BookDTO> updateBook(BookIU bookIU);

    Optional<BookDTO> saveBook(BookIU bookIU);

    boolean deleteBook(BookIU bookIU);

}