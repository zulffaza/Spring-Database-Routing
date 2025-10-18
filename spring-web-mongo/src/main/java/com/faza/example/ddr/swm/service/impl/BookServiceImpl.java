package com.faza.example.ddr.swm.service.impl;

import com.faza.example.ddr.swm.model.dto.AuthorDto;
import com.faza.example.ddr.swm.model.dto.BookDto;
import com.faza.example.ddr.swm.model.entity.Author;
import com.faza.example.ddr.swm.model.entity.Book;
import com.faza.example.ddr.swm.repository.AuthorRepository;
import com.faza.example.ddr.swm.repository.BookRepository;
import com.faza.example.ddr.swm.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream().map(this::buildBookDto).toList();
    }

    @Override
    public List<BookDto> findByAuthorName(String name) {
        Author author = authorRepository.findByName(name);
        return bookRepository.findAllByAuthor(author).stream().map(this::buildBookDto).toList();
    }

    private BookDto buildBookDto(Book book) {
        Author author = book.getAuthor();
        return BookDto.builder()
            .id(book.getId())
            .title(book.getTitle())
            .isbn(book.getIsbn())
            .author(AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build())
            .build();
    }
}
