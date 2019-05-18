package ru.pavel2107.otus.hw08.service;

import ru.pavel2107.otus.hw08.domain.Book;
import ru.pavel2107.otus.hw08.domain.Comment;

import java.util.List;

public interface BookService {
    Book save(Book author);
    void delete( String id) throws Exception;
    Book find( String id);

    List<Book> findByName(String name);
    List<Book> findBookByAuthorId( String authorID);
    Book findByISBN( String isbn);
    List<Book> findAll();

}


