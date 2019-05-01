package userapp.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import userapp.domain.entities.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
