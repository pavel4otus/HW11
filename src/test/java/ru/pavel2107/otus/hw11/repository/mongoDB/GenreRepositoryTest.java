package ru.pavel2107.otus.hw11.repository.mongoDB;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.pavel2107.otus.hw11.domain.Genre;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreRepositoryTest {


    @Autowired private GenreRepository repository;

    @Test
    public void save() {
        Genre g = new Genre();
        g.setId( "999");
        g.setName( "test genre");
        Mono<Genre> genreMono = repository.save( g);
        StepVerifier
                .create( genreMono)
                .assertNext( genre -> assertNotNull( genre.getId()))
                .expectComplete()
                .verify();
    }
}