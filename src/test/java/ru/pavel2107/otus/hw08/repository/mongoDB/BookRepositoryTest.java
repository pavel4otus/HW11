package ru.pavel2107.otus.hw08.repository.mongoDB;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pavel2107.otus.hw08.domain.Author;
import ru.pavel2107.otus.hw08.domain.Book;
import ru.pavel2107.otus.hw08.domain.Genre;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DisplayName( "MongoDB. Репозиторий книг")
@DataMongoTest
public class BookRepositoryTest {

    @Autowired private BookRepository   repository;
    @Autowired private MongoTemplate mongoTemplate;


    @Before
    public  void beforeTests(){
        Genre genre = new Genre();
        genre.setName( "FANTASY-TEST");
        genre.setId( "777");
        mongoTemplate.save( genre);

        Author author = new Author();
        author.setName( "KING-TEST");
        author.setId( "888");
        mongoTemplate.save( author);

        Book book = new Book();
        book.setId( "999");
        book.setName( "test");
        book.setIsbn( "123");
        book.setPublicationPlace( "place");
        book.setPublishingHouse( "hos");
        book.setPublicationYear(1200);

        book.setAuthor( author);
        book.setGenre( genre);

        mongoTemplate.save( book);
    }


    @Test
    public void findByIsbn() {
        Book book = repository.findByIsbn( "123");
        assertEquals( "123", book.getIsbn());
    }

    @Test
    public void findByName() {
        List<Book> books = repository.findByName( "test");
        assertEquals( "test", books.get(0).getName());
        assertEquals( 1, books.size());
    }

    @Test
    public void findBookByAuthorId() {
        List<Book> books = repository.findBookByAuthorId( "888");
        assertNotEquals( 0, books.size());
    }

    @Test
    public void findBookByGenreId() {
        List<Book> books = repository.findBookByGenreId( "777");
        assertNotEquals( 0, books.size());
    }
}