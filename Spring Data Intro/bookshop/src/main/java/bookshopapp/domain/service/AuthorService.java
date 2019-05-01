package bookshopapp.domain.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthor() throws IOException;

    List<String> getAllAuthorsOrderedByBookCount();
}
