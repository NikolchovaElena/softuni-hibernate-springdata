package userapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import userapp.domain.entities.User;
import userapp.domain.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void persistUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public List<String> getUsersByEmailProvider(String internetProvider) {
        List<User> usersList = this.userRepository.findAllByEmailEndingWith(internetProvider);
        return usersList.stream().map(u -> String.format("%s %s", u.getUsername(), u.getEmail())).collect(Collectors.toList());
    }

    @Override
    public void removeInactiveUsers(LocalDate date) {
        this.userRepository
                .findAllByLastTimeLoggedInBefore(date)
                .forEach(user -> user.setDeleted(true));
    }

}
