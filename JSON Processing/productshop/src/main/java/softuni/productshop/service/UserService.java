package softuni.productshop.service;

import softuni.productshop.domain.dtos.UserSeedDto;
import softuni.productshop.domain.dtos.view.UsersWithAtLeastOneSoldItemDto;
import softuni.productshop.domain.dtos.view.WrapperDto;
import softuni.productshop.domain.entities.User;

import java.util.List;

public interface UserService {
    void seedUsers(UserSeedDto[] userSeedDtos);

    List<UsersWithAtLeastOneSoldItemDto> getSellers();

    WrapperDto getAllSellersByCount();
}
