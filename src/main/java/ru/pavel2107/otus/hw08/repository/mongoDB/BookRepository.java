package ru.pavel2107.otus.hw08.repository.mongoDB;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.hw08.domain.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

    Book findByIsbn( String isbn);
    List<Book> findByName( String name);

    List<Book> findBookByAuthorId( String authorId);
    List<Book> findBookByGenreId( String genreId);

    boolean existsByAuthorId( String authorId);
    boolean existsByGenreId( String genreId);
}
