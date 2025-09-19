package kg.attractor.microgram.repository;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import kg.attractor.microgram.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LIkeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByFile_FileNameAndUser_Email(String fileName, String email);

}
