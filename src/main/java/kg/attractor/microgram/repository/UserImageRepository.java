package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserImageRepository extends JpaRepository<UserImage,Long> {

    Optional<UserImage> findByUser_Id(Long userId);

    Optional<UserImage> findByFileName(String fileName);
}
