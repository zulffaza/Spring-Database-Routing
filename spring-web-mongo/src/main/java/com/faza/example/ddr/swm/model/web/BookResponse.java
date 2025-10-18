package com.faza.example.ddr.swm.model.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String id;

    private String title;
    private String isbn;

    private AuthorResponse author;
}
