package userapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userapp.domain.entities.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

}
