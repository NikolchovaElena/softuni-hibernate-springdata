package softuni.productshop.service;

import softuni.productshop.domain.dtos.ProductSeedDto;
import softuni.productshop.domain.dtos.view.ProductsInRangeDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductsInRangeDto> productsInRange(BigDecimal more, BigDecimal less);


}
