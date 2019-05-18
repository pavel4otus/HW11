package ru.pavel2107.otus.hw08.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.*;
import ru.pavel2107.otus.hw08.domain.Author;
import ru.pavel2107.otus.hw08.service.AuthorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ShellComponent
public class AuthorProcessor {

    private AuthorService service;

    @Autowired
    public AuthorProcessor(AuthorService service){
        this.service = service;
    }


    @ShellMethod( value ="add author: name birthDate[dd.mm.yyyy] phone email address ", key ="author add")
    public String addAuthor(
            @ShellOption String name,  @ShellOption String bDate, @ShellOption String phone,
            @ShellOption String email, @ShellOption String address ){

        Author author = new Author();
        author.setName( name);
        author.setPhone( phone);
        author.setEmail( email);
        author.setAddress( address);
        author.setBirthDate(LocalDate.parse( bDate, DateTimeFormatter.ofPattern( "dd.MM.yyyy")));

        author = service.save( author);
        return "New author " + author.toString();

    }

    @ShellMethod( value = "list all authors", key = "author list")
    public void listAuthors(){
        for( Author author : service.findAll()){
            System.out.println( author.toString());
        }
    }

    @ShellMethod( value ="author delete authorId", key = "author delete")
    public String deleteGenre( @ShellOption String  genreId) {
        String result = "";
        try {
            service.delete(genreId);
        } catch ( Exception e){
            result =e.getMessage();
        }
        return result;
    }


}
