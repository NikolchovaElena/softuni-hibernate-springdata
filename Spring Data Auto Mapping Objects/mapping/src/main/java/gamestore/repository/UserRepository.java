package gamestore.repository;

import gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
//    @Query("SELECT new gamestore.domain.dtos.UserDto(u.email, u.isAdmin) FROM User AS u WHERE u.email = :target ")
//    User getUserByEmail(@Param(value = "target") String email);


}
