package app.domain.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlayerStatisticsID implements Serializable {
    @Column(name = "game_id")
    private Integer gameId;
    @Column(name = "player_id")
    private Integer playerId;

    public PlayerStatisticsID() {
    }

    public PlayerStatisticsID(Integer gameId, Integer playerId) {
        this.gameId = gameId;
        this.playerId = playerId;
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
