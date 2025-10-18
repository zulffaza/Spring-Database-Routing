package com.faza.example.ddr.swm.model.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String name;
    private String email;

    @ReadOnlyProperty
    @DocumentReference(lazy = true, lookup = "{'author': ?#{#self._id}}")
    private List<Book> books;
}
