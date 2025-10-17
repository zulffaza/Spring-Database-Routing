package com.faza.example.ddr.swj.repository;

import com.faza.example.ddr.swj.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
