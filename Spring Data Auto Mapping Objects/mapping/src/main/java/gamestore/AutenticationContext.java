package gamestore;

import gamestore.domain.dtos.UserDto;

public class AutenticationContext {
    private UserDto loggedUser;

    public UserDto getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserDto loggedUser) {
        this.loggedUser = loggedUser;
    }


}
