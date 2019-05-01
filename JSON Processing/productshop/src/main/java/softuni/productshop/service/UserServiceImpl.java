package softuni.productshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.productshop.domain.dtos.UserSeedDto;
import softuni.productshop.domain.dtos.view.*;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.domain.entities.User;
import softuni.productshop.repository.UserRepository;
import softuni.productshop.util.ValidatorUtil;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {

        for (UserSeedDto dto : userSeedDtos) {
            if (!this.validatorUtil.isValid(dto)) {
                this.validatorUtil
                        .violations(dto)
                        .forEach(v -> System.out.println(v.getMessage()));
                continue;
            }
            User entity = this.modelMapper.map(dto, User.class);
            this.userRepository.saveAndFlush(entity);
        }
    }

    @Override
    public List<UsersWithAtLeastOneSoldItemDto> getSellers() {
        List<User> sellersList = this.userRepository.getAllSellers();

        List<UsersWithAtLeastOneSoldItemDto> sellers = new ArrayList<>();

        for (User user : sellersList) {
            List<ProductsWithBuyersDto> goods = new ArrayList<>();

            for (Product product : user.getSellerProducts()) {
                if (product.getBuyer() != null) {
                    ProductsWithBuyersDto productsDto = new ProductsWithBuyersDto(
                            product.getName(),
                            product.getPrice(),
                            product.getBuyer().getFirstName(),
                            product.getBuyer().getLastName());

                    goods.add(productsDto);
                }
            }

            UsersWithAtLeastOneSoldItemDto seller = this.modelMapper.map(user, UsersWithAtLeastOneSoldItemDto.class);
            seller.setSoldProducts(goods);
            sellers.add(seller);
        }

        return sellers;
    }

    @Override
    public WrapperDto getAllSellersByCount() {
        List<User> sellersList = this.userRepository.getAllSellersByCount();

        List<ProductsWithCountDto> users = new ArrayList<>();

        for (User user : sellersList) {
            List<SimpleProductsDto> products = new ArrayList<>();

            for (Product product : user.getSellerProducts()) {
                if (product.getBuyer() != null) {
                    SimpleProductsDto productsDto = new SimpleProductsDto(
                            product.getName(),
                            product.getPrice());

                    products.add(productsDto);
                }
            }
            SoldPoductsDto soldProduct = new SoldPoductsDto(products.size(), products);
            ProductsWithCountDto currentUser = this.modelMapper.map(user, ProductsWithCountDto.class);
            currentUser.setSoldProducts(soldProduct);
            users.add(currentUser);
        }

        WrapperDto wrapper = new WrapperDto();
        wrapper.setUsersCount(users.size());
        wrapper.setUsers(users);

        return wrapper;
    }
}
