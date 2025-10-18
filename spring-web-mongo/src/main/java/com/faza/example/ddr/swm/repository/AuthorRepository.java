package com.faza.example.ddr.swm.repository;

import com.faza.example.ddr.swm.model.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

    Author findByName(String name);
}
