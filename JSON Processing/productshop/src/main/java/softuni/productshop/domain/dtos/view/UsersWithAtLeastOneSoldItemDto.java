package softuni.productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UsersWithAtLeastOneSoldItemDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductsWithBuyersDto> soldProducts;

    public UsersWithAtLeastOneSoldItemDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductsWithBuyersDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductsWithBuyersDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
