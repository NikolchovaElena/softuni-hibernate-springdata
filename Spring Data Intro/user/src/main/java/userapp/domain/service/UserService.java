package userapp.domain.service;

import userapp.domain.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    void persistUser(User user);

    List<String> getUsersByEmailProvider(String internetProvider);

    void removeInactiveUsers(LocalDate date);
}
