package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.ProductSeedDto;
import softuni.productshop.domain.dtos.view.ProductsInRangeDto;
import softuni.productshop.domain.entities.Category;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.domain.entities.User;
import softuni.productshop.repository.CategoryRepository;
import softuni.productshop.repository.ProductRepository;
import softuni.productshop.repository.UserRepository;
import softuni.productshop.util.ValidatorUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto dto : productSeedDtos) {
            if (!this.validatorUtil.isValid(dto)) {
                this.validatorUtil.violations(dto)
                        .forEach(v -> {
                            System.out.println(v.getMessage());
                        });

                continue;
            }

            Product entity = this.modelMapper.map(dto, Product.class);
            entity.setSeller(this.getRandomUser());

            Random random = new Random();

            if (random.nextInt() % 13 != 0) {
                entity.setBuyer(this.getRandomUser());
            }

            entity.setCategories(this.getRandomCategories());

            this.productRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<ProductsInRangeDto> productsInRange(BigDecimal more, BigDecimal less) {
        List<Product> productEntities = this.productRepository.findByPriceBetweenAndBuyerOrderByPrice(more, less, null);

        List<ProductsInRangeDto> productsInRangeDtos = new ArrayList<>();

        for (Product productEntity : productEntities) {
            ProductsInRangeDto entity = this.modelMapper.map(productEntity, ProductsInRangeDto.class);
            entity.setSeller(String.format("%s %s", productEntity.getSeller().getFirstName(), productEntity.getSeller().getLastName()));

            productsInRangeDtos.add(entity);
        }

        return productsInRangeDtos;
    }

    private User getRandomUser() {
        Random random = new Random();

        return this.userRepository.getOne(random.nextInt((int) this.userRepository.count() - 1) + 1);
    }

    private List<Category> getRandomCategories() {
        Random random = new Random();

        List<Category> categories = new ArrayList<>();

        int categoriesCount = random.nextInt((int) this.categoryRepository.count() - 1) + 1;

        for (int i = 0; i < categoriesCount; i++) {
            categories.add(this.categoryRepository.getOne(random.nextInt((int) this.categoryRepository.count() - 1) + 1));
        }

        return categories;
    }
}
