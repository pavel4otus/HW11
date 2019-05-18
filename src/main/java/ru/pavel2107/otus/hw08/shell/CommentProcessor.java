package ru.pavel2107.otus.hw08.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.hw08.domain.Book;
import ru.pavel2107.otus.hw08.domain.Comment;
import ru.pavel2107.otus.hw08.service.BookService;

import java.time.LocalDateTime;
import java.util.List;

@ShellComponent
public class CommentProcessor {

    private BookService bookService;

    @Autowired
    public CommentProcessor( BookService bookService){
        this.bookService = bookService;
    }

    @ShellMethod( value = "show comments for book", key = "comments list")
    public void showComments(@ShellOption String bookId){
        Book book = bookService.find( bookId);
        for( Comment comment : book.getComments()){
            System.out.println( comment.toString());
        }
    }

    @ShellMethod( value = "add comment for book", key = "comments add")
    public void addComment( @ShellOption String bookId, @ShellOption String userName, @ShellOption String comment ){
        Book book = bookService.find( bookId);

        Comment c = new Comment();
        c.setDateTime( LocalDateTime.now());
        c.setComment( comment);
        c.setName( userName);
        List<Comment> list = book.getComments();
        list.add( c);
        book.setComments( list);

        bookService.save( book);
    }
}
