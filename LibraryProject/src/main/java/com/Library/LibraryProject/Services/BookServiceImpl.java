package com.Library.LibraryProject.Services;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.Library.LibraryProject.Repositories.BookRepository;

import jakarta.transaction.Transactional;

import com.Library.LibraryProject.DTOs.*;
import com.Library.LibraryProject.Enums.Statuses;
import com.Library.LibraryProject.Models.Book;

@Service
public class BookServiceImpl implements IBookService{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Optional<List<BookDTO>> findAllBooks(){
        List<BookDTO> books = new ArrayList<BookDTO>();
        Iterator<Book> bookIterator = bookRepository.findAll().iterator();
        while(bookIterator.hasNext()){
            Book book = bookIterator.next();
            BookDTO bookDto = new BookDTO();
            BeanUtils.copyProperties(book, bookDto);
            books.add(bookDto);
        }
        return Optional.of(books);
    }

    @Override
    public Optional<BookDTO> findBookById(Integer id){
        Optional<Book> book = bookRepository.findById(id);
        Optional<BookDTO> bookDto = Optional.of(new BookDTO());
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    @Override
    public Optional<List<BookDTO>> findBooksByName(String name){
        Optional<List<Book>> books = bookRepository.findByName(name);
        List<BookDTO> bookDTOs = books.get().stream()
                                                    .map(book -> {
                                                        BookDTO bookDto = new BookDTO();
                                                        BeanUtils.copyProperties(book, bookDto);
                                                        return bookDto;
                                                    })
                                                    .collect(Collectors.toList());
        return Optional.of(bookDTOs);
    }

    @Override
    public Optional<List<BookDTO>> findBooksByStatuse(Statuses statuse){
        Optional<List<Book>> books = bookRepository.findByStatuse(statuse);
        List<BookDTO> bookDTOs = books.get().stream()
                                                    .map(book -> {
                                                        BookDTO bookDto = new BookDTO();
                                                        BeanUtils.copyProperties(book, bookDto);
                                                        return bookDto;
                                                    })
                                                    .collect(Collectors.toList());
        return Optional.of(bookDTOs); 
    }

    @Override
    public boolean existsBook(BookIU bookIU){
        Book book = new Book();
        BeanUtils.copyProperties(bookIU, book);
        Example<Book> bookExample = Example.of(book);
        return bookRepository.exists(bookExample);
    }

    @Override
    @Transactional
    public Optional<BookDTO> updateBook(BookIU bookIU){
        Optional<BookDTO> optionalBookDto = Optional.ofNullable(new BookDTO());
        if(existsBook(bookIU)){
            Book book = new Book();
            BeanUtils.copyProperties(bookIU, book);
            BeanUtils.copyProperties(bookRepository.updateBook(book).get(), optionalBookDto);
        }
        return optionalBookDto;
    }

    @Override
    @Transactional
    public Optional<BookDTO> saveBook(BookIU bookIU){
        Optional<BookDTO> optionalBookDto = Optional.ofNullable(new BookDTO());
        if(!existsBook(bookIU)){
            Book book = new Book();
            BeanUtils.copyProperties(bookIU, book);
            bookRepository.save(book);
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book, bookDTO);
            optionalBookDto = Optional.of(bookDTO); 
        }
        return optionalBookDto;
    }

    @Override
    @Transactional
    public boolean deleteBook(BookIU bookIU){
        if(existsBook(bookIU)){
            Book book = new Book();
            BeanUtils.copyProperties(bookIU, book);
            bookRepository.delete(book);
            return true;
        }
        return false;
    }

    
    
}
