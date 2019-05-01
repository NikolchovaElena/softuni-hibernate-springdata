package softuni.productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ProductsWithCountDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer age;
    @Expose
    private SoldPoductsDto soldProducts;

    public ProductsWithCountDto() {
    }

    public ProductsWithCountDto(String firstName, String lastName, Integer age, SoldPoductsDto soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SoldPoductsDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldPoductsDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
