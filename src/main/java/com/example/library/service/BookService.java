package com.example.library.service;


import com.example.library.Dto.BookDTO;
import com.example.library.Repository.BookRepository;
import com.example.library.models.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public BookDTO createBook(BookDTO bookDTO){
        Book book = new Book();
        BeanUtils.copyProperties(bookDTO,book);
        Book savedBook = bookRepository.save(book);

        BookDTO savedBookDto = new BookDTO();
        BeanUtils.copyProperties(savedBook,savedBookDto);
        return savedBookDto;
    }
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = new BookDTO();
                    BeanUtils.copyProperties(book,bookDTO);
                    return bookDTO;
                })
                .collect(Collectors.toList());
    }
}
