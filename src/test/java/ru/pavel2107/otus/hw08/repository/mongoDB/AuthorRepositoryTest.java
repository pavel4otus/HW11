package ru.pavel2107.otus.hw08.repository.mongoDB;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.pavel2107.otus.hw08.domain.Author;
import ru.pavel2107.otus.hw08.service.BookServiceImpl;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DisplayName( "MongoDB. Репозиторий авторов")
@DataMongoTest
public class AuthorRepositoryTest {

    @Autowired AuthorRepository repository;


    @Test
    public void findByName() {
        Author author = new Author();
        author.setName( "king");
        repository.save( author);

        List<Author> list = repository.findByName( "king");
        assertEquals( "king", list.get(0).getName());


    }
}