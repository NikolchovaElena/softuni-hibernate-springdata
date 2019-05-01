package app.domain.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "bet_games")
public class BetGame {

    @EmbeddedId
    private BetGamesID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("bet_id")
    private Bet bet;


    @OneToOne()
    @JoinColumn(name = "result_prediction",
            referencedColumnName = "id")
    private ResultPrediction prediction;

    public BetGame() {
    }

    public BetGame(Game game, Bet bet) {
        this.game = game;
        this.bet = bet;
        this.id = new BetGamesID(game.getId(), bet.getId());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    }

