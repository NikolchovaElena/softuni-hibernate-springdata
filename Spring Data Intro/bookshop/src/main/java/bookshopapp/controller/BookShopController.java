package bookshopapp.controller;

import bookshopapp.domain.service.AuthorService;
import bookshopapp.domain.service.BookService;
import bookshopapp.domain.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class BookShopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookShopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.authorService.seedAuthor();
        this.categoryService.seedCategories();
        this.bookService.seedBooks();

        //  this.bookTitles();
        //  this.authorNames();
     //  bookByNumberOfBooks();
        // bookByAuthorName();
    }

    private void bookTitles() {
        List<String> bookTitles = this.bookService.getAllBooksTitlesAfter();
        System.out.println(String.join("\r\n", bookTitles));
    }

    private void authorNames() {
        this.bookService.getAllAuthorsWithBooksBefore().forEach(System.out::println);
    }

    private void bookByNumberOfBooks() {
     this.authorService.getAllAuthorsOrderedByBookCount().forEach(System.out::println);
    }

    private void bookByAuthorName() {
        this.bookService.getAllBooksByAuthorName().forEach(System.out::println);
    }

}
