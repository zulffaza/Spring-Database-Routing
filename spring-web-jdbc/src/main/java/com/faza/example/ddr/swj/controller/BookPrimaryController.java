package com.faza.example.ddr.swj.controller;

import com.faza.example.ddr.swj.controller.helper.BookControllerHelper;
import com.faza.example.ddr.swj.model.annotation.RouteDataSource;
import com.faza.example.ddr.swj.model.constant.DatabaseConstant;
import com.faza.example.ddr.swj.model.web.BookResponse;
import com.faza.example.ddr.swj.model.web.base.BaseResponse;
import com.faza.example.ddr.swj.service.BookService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primary/books")
public class BookPrimaryController extends BaseBookController {

    public BookPrimaryController(BookService bookService,
        BookControllerHelper bookControllerHelper) {
        super(bookService, bookControllerHelper);
    }

    @RouteDataSource(name = DatabaseConstant.PRIMARY_ROUTE)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<BookResponse>> findAll() {
        return super.findAll();
    }

    @RouteDataSource(name = DatabaseConstant.PRIMARY_ROUTE)
    @GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<BookResponse>> findByAuthorName(@RequestParam String name) {
        return super.findByAuthorName(name);
    }
}
