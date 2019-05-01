package gamestore.service;

import gamestore.domain.entities.Game;
import gamestore.domain.entities.User;
import gamestore.repository.GameRepository;
import gamestore.service.api.GameService;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;
    private Validator validator;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.getValidator();
    }

    private void getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void addGame(Game game) {
        if (this.checkIfGameExist(game.getTitle())) {
            throw new IllegalArgumentException("Game already exists!");
        }
        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<Game>> violations = validator.validate(game);

        if (violations.size() == 0) {
            this.gameRepository.save(game);
        } else {
            violations.forEach(err ->
                    sb.append(err.getMessage())
                            .append(System.lineSeparator()));
            System.out.println(sb.toString().trim());
        }
    }

    public boolean checkIfGameExist(String title) {
        Game game = this.gameRepository.findByTitle(title);
        return game != null;
    }

}
