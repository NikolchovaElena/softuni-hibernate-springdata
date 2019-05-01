package userapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userapp.domain.entities.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Integer> {
}
