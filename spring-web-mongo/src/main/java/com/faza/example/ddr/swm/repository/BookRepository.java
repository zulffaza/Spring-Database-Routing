package com.faza.example.ddr.swm.repository;

import com.faza.example.ddr.swm.model.entity.Author;
import com.faza.example.ddr.swm.model.entity.Book;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthor(Author author);
}
