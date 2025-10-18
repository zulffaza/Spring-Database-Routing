package com.faza.example.ddr.swm.service;

import com.faza.example.ddr.swm.model.dto.BookDto;
import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    List<BookDto> findByAuthorName(String name);
}
