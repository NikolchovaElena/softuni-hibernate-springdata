package userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import userapp.domain.entities.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import userapp.domain.service.UserService;

import java.time.LocalDate;

@Controller
public class UserController implements CommandLineRunner {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {

        addUser();
        //   printAllUsersByEmailProvider("abv@abv.bg");
        //  //not deleted but setdDeleted == true
        //  removeAllInactiveUsers(LocalDate.of(2003,10,12));

    }

    private void addUser() {

        User user2 = new User();
        user2.setUsername("pesho");
        user2.setPassword("aA2@asdfgh");
        //  user2.setPassword("a");
        user2.setEmail("sdf@sdfg.dfh");
        //   user2.setEmail("sdfsdfg.dfh");
        //   user2.setAge(5);
        //  user2.setLastTimeLoggedIn(LocalDate.of(2004, 10, 12));
        userService.persistUser(user2);
    }

    private void printAllUsersByEmailProvider(String internetProvider) {
        this.userService.getUsersByEmailProvider(internetProvider).forEach(System.out::println);
    }

    private void removeAllInactiveUsers(LocalDate date) {
        this.userService.removeInactiveUsers(date);
    }

}
