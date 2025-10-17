package com.faza.example.ddr.swj.service;

import com.faza.example.ddr.swj.model.dto.BookDto;
import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    List<BookDto> findByAuthorName(String name);
}
