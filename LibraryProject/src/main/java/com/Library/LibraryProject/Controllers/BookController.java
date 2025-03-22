package com.Library.LibraryProject.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Library.LibraryProject.DTOs.BookDTO;
import com.Library.LibraryProject.DTOs.BookIU;
import com.Library.LibraryProject.Enums.Statuses;
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
    public ResponseEntity<List<BookDTO>> getBooksByStatuse(@PathVariable Statuses status){
        return bookService.findBooksByStatuse(status).map(ResponseEntity::ok)
                                                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<BookDTO> addBook(BookIU bookIu){
        Optional<BookDTO> book = bookService.existsBook(bookIu)? Optional.empty(): bookService.saveBook(bookIu);
        return book.map(b -> ResponseEntity.status(HttpStatus.CREATED).body(b)).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<BookDTO> updateBook(@RequestBody BookIU bookIu, @PathVariable Integer id){
        return bookService.updateBook(id, bookIu).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<HttpStatus> deleteBook(@RequestBody BookIU bookIU){
        HttpStatus status = bookService.deleteBook(bookIU)? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).build();
    }
}
