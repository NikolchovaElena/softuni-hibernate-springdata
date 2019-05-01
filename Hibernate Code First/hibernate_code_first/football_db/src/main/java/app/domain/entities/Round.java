package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Round {
    private enum Rounds {
        Groups,
        League,
        SemiFinal,
        Final
    }

    private Integer id;
    private Rounds name;
    private Set<Game> games;

    public Round() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    public Rounds getName() {
        return name;
    }

    public void setName(Rounds name) {
        this.name = name;
    }

    @OneToMany(mappedBy ="round",targetEntity= Game.class)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
