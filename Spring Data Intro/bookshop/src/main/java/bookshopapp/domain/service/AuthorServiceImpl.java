package bookshopapp.domain.service;

import bookshopapp.domain.entities.Author;
import bookshopapp.domain.entities.Book;
import bookshopapp.domain.repository.AuthorRepository;
import bookshopapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final String AUTHORS_FILE_PATH = "C:\\Users\\elena n\\Desktop\\isstr\\bookshop\\src\\main\\resources\\files\\authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    //for simplicity, either add all or none
    @Override
    public void seedAuthor() throws IOException {
        if (this.authorRepository.count() != 0) {
            return;
        }

        String[] authorFileContent = this.fileUtil.getFileContent(AUTHORS_FILE_PATH);

        for (String line : authorFileContent) {
            String[] params = line.split("\\s+");

            Author author = new Author();
            author.setFirstName(params[0]);
            author.setLastName(params[1]);

            this.authorRepository.saveAndFlush(author);
        }
    }

    public List<String> getAllAuthorsOrderedByBookCount() {
        List<Author> authors = this.authorRepository.findAll();
        List<String> result = new ArrayList<>();

        authors.stream().sorted((a, b) -> b.getBooks().size() - a.getBooks().size()).forEach(a -> {
            result.add(String.format("%s %s %d", a.getFirstName(), a.getLastName(), a.getBooks().size()));
        });

        return result;
    }


}
