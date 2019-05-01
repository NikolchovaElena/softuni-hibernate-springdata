package app.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Bet")
@Table(name = "bets")
public class Bet {
    private Integer id;
    private BigDecimal betMoney;
    private LocalDate dateOfBet;
    private User user;

    @OneToMany(
            mappedBy = "bets",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<BetGame> games = new HashSet<>();

    public void addTag(Game game) {

    }

    public void removeTag(Game game) {

    }

    public Bet() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "bet_money")
    public BigDecimal getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    @Column(name = "date_time")
    public LocalDate getDateOfBet() {
        return dateOfBet;
    }

    public void setDateOfBet(LocalDate dateOfBet) {
        this.dateOfBet = dateOfBet;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
