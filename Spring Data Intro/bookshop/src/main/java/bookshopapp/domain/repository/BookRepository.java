package bookshopapp.domain.repository;

import bookshopapp.domain.entities.Author;
import bookshopapp.domain.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);



    List<Book> findAllByAuthor_FirstNameAndAuthor_LastName(String firstName, String lastName);

}
