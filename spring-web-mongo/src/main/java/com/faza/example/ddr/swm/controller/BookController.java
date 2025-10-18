package com.faza.example.ddr.swm.controller;

import com.faza.example.ddr.swm.controller.helper.BookControllerHelper;
import com.faza.example.ddr.swm.model.web.BookResponse;
import com.faza.example.ddr.swm.model.web.base.BaseResponse;
import com.faza.example.ddr.swm.service.BookService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController extends BaseBookController {

    public BookController(BookService bookService, BookControllerHelper bookControllerHelper) {
        super(bookService, bookControllerHelper);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<BookResponse>> findAll() {
        return super.findAll();
    }

    @GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<BookResponse>> findByAuthorName(@RequestParam String name) {
        return super.findByAuthorName(name);
    }
}
