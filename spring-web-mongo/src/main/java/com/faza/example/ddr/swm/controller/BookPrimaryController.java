package com.faza.example.ddr.swm.controller;

import com.faza.example.ddr.swm.controller.helper.BookControllerHelper;
import com.faza.example.ddr.swm.model.annotation.RouteDataSource;
import com.faza.example.ddr.swm.model.constant.DatabaseConstant;
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
