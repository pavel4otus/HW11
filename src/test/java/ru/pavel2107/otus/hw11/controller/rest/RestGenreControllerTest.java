package ru.pavel2107.otus.hw11.controller.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestGenreControllerTest {


    @Autowired RestGenreController controller;

    @Test
    public void listGenres() {

        WebTestClient client = WebTestClient.bindToController( controller)
                .build();


        client
                .get()
                .uri( "/flux/genres")
                .exchange()
                .expectStatus()
                .isOk();

    }
}