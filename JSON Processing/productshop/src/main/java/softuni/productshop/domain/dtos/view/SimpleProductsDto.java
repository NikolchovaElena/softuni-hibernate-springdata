package softuni.productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SimpleProductsDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public SimpleProductsDto() {
    }

    public SimpleProductsDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
