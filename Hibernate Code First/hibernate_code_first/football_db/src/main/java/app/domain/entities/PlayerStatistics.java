package app.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistics {

    @EmbeddedId
    private PlayerStatisticsID id;

    @Column(name = "scored_goals")
    private Integer scoredGoals;
    @Column(name = "player_assists")
    private String playerAssists;
    @Column(name = "played_minutes")
    private Integer playedMinutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("player_id")
    private Player player;

    public PlayerStatistics() {
    }

    public PlayerStatistics(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.id = new PlayerStatisticsID(game.getId(), player.getId());
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
