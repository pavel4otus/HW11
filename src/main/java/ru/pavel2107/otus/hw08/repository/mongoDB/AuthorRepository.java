package ru.pavel2107.otus.hw08.repository.mongoDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.hw08.domain.Author;


import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {
    public List<Author> findByName( String name);
}

