package ru.pavel2107.otus.hw08.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document( collection = "books")
public @ToString class Book {

    @Setter @Getter @Id private String id;
    @Setter @Getter private String name;
    @Setter @Getter @DBRef  private Author author;
    @Setter @Getter @DBRef  private Genre genre;
    @Setter @Getter private String    publishingHouse;
    @Setter @Getter private Integer   publicationYear;
    @Setter @Getter private String    publicationPlace;
    @Setter @Getter private String    isbn;
    @Setter @Getter private List<Comment> comments;

}
