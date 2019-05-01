package softuni.productshop.domain.dtos.view;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class SoldPoductsDto {
    @Expose
    private Integer count;
    @Expose
    private List<SimpleProductsDto> products;

    public SoldPoductsDto() {
        this.products=new ArrayList<>();
    }

    public SoldPoductsDto(Integer count, List<SimpleProductsDto> products) {
        this.count = count;
        this.products = products;
    }
}
