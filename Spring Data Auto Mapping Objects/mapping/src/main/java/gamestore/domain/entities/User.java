package gamestore.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
public class User extends BaseEntity {

    private String email;
    private String password;
    private String fullName;
    private Set<Game> order;
    private Set<Game> game;
    private boolean isAdmin;

    public User() {
    }

    @Column(name = "email", nullable = false, unique = true)
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-z]{2,4}", message = "Invalid Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    @Pattern(regexp = "(?=^.{6,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Invalid Password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @OneToMany(targetEntity = Order.class)
    public Set<Game> getOrder() {
        return order;
    }

    public void setOrder(Set<Game> order) {
        this.order = order;
    }

    @ManyToMany(targetEntity = Game.class)
    @JoinTable(name = "users_games",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    public Set<Game> getGame() {
        return game;
    }

    public void setGame(Set<Game> game) {
        this.game = game;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
