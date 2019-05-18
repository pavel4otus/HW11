package ru.pavel2107.otus.hw08.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.hw08.domain.Genre;
import ru.pavel2107.otus.hw08.service.GenreService;

@ShellComponent
public class GenreProcessor {

    private GenreService service;

    @Autowired
    public GenreProcessor(GenreService service){
        this.service = service;
    }

    @ShellMethod( value = "add genre", key = "genre add")
    public String addGenre(
            @ShellOption String name){

        Genre genre = new Genre();
        genre.setName( name);
        genre = service.save( genre);
        return  "created genre " + name + " with (id)=" + genre.getId();
    }

    @ShellMethod( value = "genres list", key = "genre list")
    public void listGenre(){

        for( Genre g: service.findAll()){
            System.out.println( g.toString());
        }
    }

    @ShellMethod( value ="genre delete genreId", key = "genre delete")
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


