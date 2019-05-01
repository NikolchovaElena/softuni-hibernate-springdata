package app.domain.entities;

import javax.persistence.*;
import java.util.Map;

@Entity(name = "result_predictions")
public class ResultPrediction {
    private enum Prediction {
        HomeTeamWin,
        DrawGame,
        AwayTeamWin,
    }

    private Integer id;
    private Prediction prediction;

      public ResultPrediction() {
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
    @Column(name = "prediction")
    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}

