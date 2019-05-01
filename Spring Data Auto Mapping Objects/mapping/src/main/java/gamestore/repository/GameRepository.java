package gamestore.repository;

import gamestore.domain.entities.Game;
import gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {

    Game findByTitle(String title);

}
