package gamestore.service.api;

import gamestore.domain.dtos.*;
import gamestore.domain.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface UserService {

    void registerUser(User user, String confirmPassword);

    boolean checkIfUserExists(String email);

    UserDto getUserDtoByEmail(String email);


}
