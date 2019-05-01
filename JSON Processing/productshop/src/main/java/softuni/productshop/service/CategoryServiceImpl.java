package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.CategorySeedDto;
import softuni.productshop.domain.dtos.view.CategoriesByProductsCountDto;
import softuni.productshop.domain.entities.Category;
import softuni.productshop.repository.CategoryRepository;
import softuni.productshop.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedCategories(CategorySeedDto[] categorySeedDtos) {
        for (CategorySeedDto dto : categorySeedDtos) {
            if (!this.validatorUtil.isValid(dto)) {
                this.validatorUtil
                        .violations(dto)
                        .forEach(v -> System.out.println(v.getMessage()));
                continue;
            }
            Category entity = this.modelMapper.map(dto, Category.class);
            this.categoryRepository.saveAndFlush(entity);
        }
    }


    @Override
    public List<CategoriesByProductsCountDto> getCategoriesByProductsCount() {
        return this.categoryRepository.getCategoriesByProductsCount();
    }


}
