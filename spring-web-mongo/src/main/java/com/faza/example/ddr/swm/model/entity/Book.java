package com.faza.example.ddr.swm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String title;
    private String isbn;

    @DocumentReference(lazy = true)
    private Author author;
}
