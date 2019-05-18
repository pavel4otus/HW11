package ru.pavel2107.otus.hw08.service;

import ru.pavel2107.otus.hw08.domain.Genre;

import java.util.List;

public interface GenreService {

    Genre save( Genre genre);
    void delete( String id) throws Exception;
    Genre find(String id);
    List<Genre> findAll();

}
