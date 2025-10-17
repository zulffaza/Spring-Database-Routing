package com.faza.example.ddr.swj.controller;

import com.faza.example.ddr.swj.controller.helper.BookControllerHelper;
import com.faza.example.ddr.swj.model.web.BookResponse;
import com.faza.example.ddr.swj.model.web.base.BaseResponse;
import com.faza.example.ddr.swj.service.BookService;
import java.util.List;

abstract class BaseBookController {

    private final BookService bookService;
    private final BookControllerHelper bookControllerHelper;

    public BaseBookController(BookService bookService, BookControllerHelper bookControllerHelper) {
        this.bookService = bookService;
        this.bookControllerHelper = bookControllerHelper;
    }

    public BaseResponse<List<BookResponse>> findAll() {
        List<BookResponse> bookResponses =
            bookService.findAll().stream().map(bookControllerHelper::buildBookDto).toList();
        return BaseResponse.<List<BookResponse>>builder().data(bookResponses).build();
    }

    public BaseResponse<List<BookResponse>> findByAuthorName(String name) {
        List<BookResponse> bookResponses = bookService.findByAuthorName(name)
            .stream()
            .map(bookControllerHelper::buildBookDto)
            .toList();
        return BaseResponse.<List<BookResponse>>builder().data(bookResponses).build();
    }
}
