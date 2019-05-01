package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Competition {
    private Integer id;
    private String name;
    private CompetitionType competitionType;
    private Set<Game> games;

    public Competition() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne()
    @JoinColumn(name = "competition_type",
            referencedColumnName = "id")
        public CompetitionType getCompetitionType() {
        return competitionType;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }

    @OneToMany(mappedBy ="competition",targetEntity= Game.class)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }
}
