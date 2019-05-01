package softuni.productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class WrapperDto {
    @Expose
    private Integer usersCount;
    @Expose
    private List<ProductsWithCountDto> users;

    public void setUsersCount() {
        this.users = new ArrayList<>();
    }

    public Integer getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(Integer usersCount) {
        this.usersCount = usersCount;
    }

    public List<ProductsWithCountDto> getUsers() {
        return users;
    }

    public void setUsers(List<ProductsWithCountDto> users) {
        this.users = users;
    }
}
