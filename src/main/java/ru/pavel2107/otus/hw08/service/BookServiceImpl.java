package ru.pavel2107.otus.hw08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.pavel2107.otus.hw08.domain.Book;
import ru.pavel2107.otus.hw08.domain.Comment;
import ru.pavel2107.otus.hw08.repository.mongoDB.AuthorRepository;
import ru.pavel2107.otus.hw08.repository.mongoDB.BookRepository;
import ru.pavel2107.otus.hw08.repository.mongoDB.GenreRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository   bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository  genreRepository;


    @Autowired
    public BookServiceImpl( BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository  genreRepository){
        this.bookRepository   = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository  = genreRepository;
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookRepository.findByIsbn( isbn);
    }

    @Override
    public Book save(Book book) {
        Book b = null;
        if( book.getAuthor() != null && authorRepository.findById( book.getAuthor().getId()) != null){
            if( book.getGenre() != null && genreRepository.findById( book.getGenre().getId()) != null){
                b = bookRepository.save( book);
            }
        }
        return b;
    }

    @Override
    public void delete(String id) {
        Book book = find( id);
        if( book != null) {
            bookRepository.delete(book);
        }
    }

    @Override
    public Book find(String id) {
        return bookRepository.findById( id).orElse( null);
    }

    @Override
    public List<Book> findByName(String name) {
        return bookRepository.findByName( name);
    }

    @Override
    public List<Book> findBookByAuthorId(String authorId) {
        return bookRepository.findBookByAuthorId( authorId);
    }

    @Override
    public List<Book> findAll() {
        return (List<Book> ) bookRepository.findAll();
    }
}
