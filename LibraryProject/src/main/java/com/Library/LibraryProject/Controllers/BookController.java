package com.Library.LibraryProject.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Library.LibraryProject.DTOs.BookDTO;
import com.Library.LibraryProject.Services.IBookService;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping(path = "/all")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return bookService.findAllBooks().map(ResponseEntity::ok)
                                        .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{name}")
    public ResponseEntity<List<BookDTO>> getBooksByName(@PathVariable String name){
        return bookService.findBooksByName(name).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id){
        return bookService.findBookById(id).map(ResponseEntity::ok)
                                            .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author){
        return bookService.findBooksByAuthor(author).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByStatuse(@PathVariable String author){
        return bookService.findBooksByAuthor(author).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
