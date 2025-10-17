package com.faza.example.ddr.swj.repository;

import com.faza.example.ddr.swj.model.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor_Name(String name);
}
