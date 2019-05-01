package userapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import userapp.domain.entities.User;

import java.time.LocalDate;

@SpringBootApplication
public class UserApp {
    public static void main(String[] args) {


        SpringApplication.run(UserApp.class, args);

    }
}
