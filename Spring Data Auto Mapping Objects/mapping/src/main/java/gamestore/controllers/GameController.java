package gamestore.controllers;

import gamestore.domain.entities.Game;
import gamestore.service.api.GameService;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameController {
    private GameService gameService;


    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public String add(String title, BigDecimal price, Double size,
                      String trailer, String imageThumbnail,
                      String description, LocalDate releaseDate) {

        Game game = new Game();
        game.setTitle(title);
        game.setPrice(price);
        game.setSize(size);
        game.setTrailer(trailer);
        game.setImageThumbnail(imageThumbnail);
        game.setDescription(description);
        game.setReleaseDate(releaseDate);

        try {
            this.gameService.addGame(game);
        } catch (IllegalArgumentException iae) {
            return iae.getMessage();
        }

        return "Game added successfully!";
    }

    public String edit(Integer id, String params) {
        return null;
    }
}
