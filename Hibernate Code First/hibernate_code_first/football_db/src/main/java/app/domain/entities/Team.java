package app.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Team {
    private enum Initials {
        HomeTeamWin,
        DrawGame,
        AwayTeamWin,
    }

    private Integer id;
    private String name;
    private String logo;
    private Initials initials;
    private BigDecimal budget;
    private Color primaryColor;
    private Color secondaryColor;
    private Town town;
    private Set<Player> players;
    private Set<Game> homeGames;
    private Set<Game> awayGames;

    public Team() {
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

    @Column(name = "logo")
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "initials")
    public Initials getInitials() {
        return initials;
    }

    public void setInitials(Initials initials) {
        this.initials = initials;
    }

    @Column(name = "budget")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @OneToOne()
    @JoinColumn(name = "primary_kit_color",
            referencedColumnName = "id")
    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }

    @OneToOne()
    @JoinColumn(name = "secondary_kit_color",
            referencedColumnName = "id")
    public Color getSecondaryColor() {
        return secondaryColor;
    }


    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    @ManyToOne()
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    @OneToMany(mappedBy ="team",targetEntity= Player.class)
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @OneToMany(mappedBy ="homeTeam",targetEntity= Game.class)
    public Set<Game> getHomeGames() {
        return homeGames;
    }

    public void setHomeGames(Set<Game> homeGames) {
        this.homeGames = homeGames;
    }

    @OneToMany(mappedBy ="awayTeam",targetEntity= Game.class)
    public Set<Game> getAwayGames() {
        return awayGames;
    }

    public void setAwayGames(Set<Game> awayGames) {
        this.awayGames = awayGames;
    }
}
