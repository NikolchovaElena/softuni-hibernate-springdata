package gamestore.service;

import gamestore.domain.dtos.UserDto;
import gamestore.domain.entities.User;
import gamestore.repository.UserRepository;
import gamestore.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private Validator validator;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.getValidator();
        modelMapper = new ModelMapper();
    }

    private void getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public void registerUser(User user, String confirmPassword) {
        if (checkIfUserExists(user.getEmail())) {
            throw new IllegalArgumentException("User already exists!");
        }

        if (!user.getPassword().equals(confirmPassword)) {
            throw new IllegalArgumentException("Passwords don't match!");
        }

        StringBuilder sb = new StringBuilder();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.size() == 0) {
            this.userRepository.save(user);
        } else {
            violations.forEach(err ->
                    sb.append(err.getMessage())
                            .append(System.lineSeparator()));
            System.out.println(sb.toString().trim());
        }
    }

    @Override
    public boolean checkIfUserExists(String email) {
        return this.userRepository.findByEmail(email) != null;
    }


    @Override
    public UserDto getUserDtoByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        UserDto dto = new UserDto();
        this.modelMapper.map(user, dto);
        return dto;
    }


}
