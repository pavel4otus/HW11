package ru.pavel2107.otus.hw08.service;

import ru.pavel2107.otus.hw08.domain.Author;

import java.util.List;

public interface AuthorService {
    Author save(Author author);
    void delete( String id) throws Exception;
    Author find( String id);
    List<Author> findByName(String name);
    List<Author> findAll();
}
