package softuni.productshop.service;


import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.dtos.view.CategoriesByProductsCountDto;

import java.util.List;

public interface CategoryService {
    void seedCategories(CategorySeedDto[] categorySeedDtos);

    List<CategoriesByProductsCountDto> getCategoriesByProductsCount();

}
