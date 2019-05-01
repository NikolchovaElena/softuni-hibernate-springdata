package bookshopapp.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "authors")
public class Author extends BaseEntity {

    private String firstName;
    private String lastName;
    private Set<Book> books;

    public Author() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    @OneToMany(mappedBy = "author", targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
