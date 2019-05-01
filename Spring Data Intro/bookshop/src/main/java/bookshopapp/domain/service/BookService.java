package bookshopapp.domain.service;

import bookshopapp.domain.entities.Book;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBooksBefore();



    List<String> getAllBooksByAuthorName();

    }
