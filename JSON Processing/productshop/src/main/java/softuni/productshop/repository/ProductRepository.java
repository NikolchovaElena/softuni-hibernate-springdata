package softuni.productshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.productshop.domain.entities.Product;
import softuni.productshop.domain.entities.User;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByPriceBetweenAndBuyerOrderByPrice(BigDecimal more, BigDecimal less, User user);

//    @Query("SELECT u FROM User u JOIN u.id ")
//    List<User> findUsersWithAtLeastOneSell();

}
