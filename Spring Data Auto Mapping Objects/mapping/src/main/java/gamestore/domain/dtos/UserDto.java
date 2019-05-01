package gamestore.domain.dtos;

public class UserDto {
    private String email;
    private boolean isAdmin;

    public UserDto() {
    }

    public UserDto(String email, boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
