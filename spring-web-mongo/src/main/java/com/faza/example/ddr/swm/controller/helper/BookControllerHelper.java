package com.faza.example.ddr.swm.controller.helper;

import com.faza.example.ddr.swm.model.dto.AuthorDto;
import com.faza.example.ddr.swm.model.dto.BookDto;
import com.faza.example.ddr.swm.model.web.AuthorResponse;
import com.faza.example.ddr.swm.model.web.BookResponse;
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
