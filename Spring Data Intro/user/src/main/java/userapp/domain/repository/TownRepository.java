package userapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userapp.domain.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
}
