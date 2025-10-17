package com.faza.example.ddr.swj.controller;

import com.faza.example.ddr.swj.controller.helper.BookControllerHelper;
import com.faza.example.ddr.swj.model.web.BookResponse;
import com.faza.example.ddr.swj.model.web.base.BaseResponse;
import com.faza.example.ddr.swj.service.BookService;
import com.faza.example.ddr.swj.service.DatabaseRoutingService;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/routes/books")
public class BookRoutingController extends BaseBookController {

    private final DatabaseRoutingService databaseRoutingService;

    public BookRoutingController(BookService bookService,
        BookControllerHelper bookControllerHelper,
        DatabaseRoutingService databaseRoutingService) {
        super(bookService, bookControllerHelper);
        this.databaseRoutingService = databaseRoutingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<BookResponse>> findAll(@RequestParam(required = false) String databaseRouteName) {
        return databaseRoutingService.execute(databaseRouteName, super::findAll);
    }

    @GetMapping(value = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse<List<BookResponse>> findByAuthorName(@RequestParam(required = false) String databaseRouteName,
        @RequestParam String name) {
        return databaseRoutingService.execute(databaseRouteName,
            () -> super.findByAuthorName(name));
    }
}
