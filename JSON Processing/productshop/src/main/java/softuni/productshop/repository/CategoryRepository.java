package softuni.productshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.productshop.domain.dtos.view.CategoriesByProductsCountDto;
import softuni.productshop.domain.entities.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT new softuni.productshop.domain.dtos.view.CategoriesByProductsCountDto" +
            "(c.name,SIZE(c.products), AVG(p.price), SUM(p.price))" +
            "FROM Category AS c JOIN c.products AS p GROUP BY c.id ORDER BY SIZE(c.products) DESC")
    List<CategoriesByProductsCountDto> getCategoriesByProductsCount();


}
