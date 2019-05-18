package ru.pavel2107.otus.hw08.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.pavel2107.otus.hw08.domain.Book;
import ru.pavel2107.otus.hw08.service.AuthorService;
import ru.pavel2107.otus.hw08.service.BookService;
import ru.pavel2107.otus.hw08.service.GenreService;
                            
import java.util.List;

@ShellComponent
public class BookProcessor {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public BookProcessor(BookService bookService, AuthorService authorService, GenreService genreService){
        this.bookService   = bookService;
        this.authorService = authorService;
        this.genreService  = genreService;
    }


    @ShellMethod( value ="book add isbn bookName authorId publicationYear publicationPlace publishingHouse genreId", key="book add")
    public String addBook(
            @ShellOption String isbn, @ShellOption String name, @ShellOption String authorId,
            @ShellOption Integer pubYear, @ShellOption String pubPlace, @ShellOption String pubHouse,
            @ShellOption String genreId){

        Book book = new Book();
        book.setIsbn( isbn);
        book.setName( name);
        book.setPublicationPlace( pubPlace);
        book.setPublicationYear( pubYear);
        book.setAuthor( authorService.find( authorId));
        book.setGenre(  genreService.find( genreId));

        bookService.save( book);
        return "You added new book " + book.toString();
    }


    @ShellMethod( value = "show all books", key ="book list")
    public void listBooks(){
         for( Book book : bookService.findAll()){
             System.out.println( book.toString());
         }
    }

   @ShellMethod( value = "find book by id", key ="book find by id")
   public String find(@ShellOption String bookId){
        Book book = bookService.find( bookId);
        return book == null ? "Book id # " + bookId + " not found" : book.toString();
   }

    @ShellMethod( value = "find book by isbn", key ="book find by isbn")
    public String findByISBN(@ShellOption String isbn){
        Book book = bookService.findByISBN( isbn);
        return book == null ? "Book isbn # " + isbn + " not found" : book.toString();
    }

    @ShellMethod( value = "find book by authorid", key ="book find by authorid")
    public String findByAuthorID(@ShellOption String authorId){
        List<Book> books = bookService.findBookByAuthorId( authorId);
        String result = "";
        if( books == null || books.size() == 0){
            result = "No books for this author";
        } else{
            for ( Book book : books){
                result = result + book.toString() + "\n";
            }
        }

        return result;
    }

    @ShellMethod( value ="book delete bookId", key = "genre bookId")
    public String deleteGenre( @ShellOption String  genreId) {
        String result = "";
        try {
            bookService.delete(genreId);
        } catch ( Exception e){
            result =e.getMessage();
        }
        return result;
    }

}


