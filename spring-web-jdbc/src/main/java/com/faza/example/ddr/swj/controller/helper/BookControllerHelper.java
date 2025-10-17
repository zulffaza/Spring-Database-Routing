package com.faza.example.ddr.swj.controller.helper;

import com.faza.example.ddr.swj.model.dto.AuthorDto;
import com.faza.example.ddr.swj.model.dto.BookDto;
import com.faza.example.ddr.swj.model.web.AuthorResponse;
import com.faza.example.ddr.swj.model.web.BookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookControllerHelper {

    public BookResponse buildBookDto(BookDto bookDto) {
        AuthorDto author = bookDto.getAuthor();
        return BookResponse.builder()
            .id(bookDto.getId())
            .title(bookDto.getTitle())
            .isbn(bookDto.getIsbn())
            .author(AuthorResponse.builder()
                .id(author.getId())
                .name(author.getName())
                .email(author.getEmail())
                .build())
            .build();
    }

}
