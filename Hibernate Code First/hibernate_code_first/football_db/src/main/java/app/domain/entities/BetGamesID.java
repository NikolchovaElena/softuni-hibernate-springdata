package app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BetGamesID implements Serializable {
    @Column(name = "game_id")
    private Integer gameId;
    @Column(name = "bet_id")
    private Integer betId;

    public BetGamesID() {
    }

    public BetGamesID(Integer gameId, Integer betId) {
        this.gameId = gameId;
        this.betId = betId;
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
