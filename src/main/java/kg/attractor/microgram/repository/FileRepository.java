package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findByFileName(String fileName);

    List<File> findAllFilesByUser_Email(String userEmail);

    @Query("SELECT f FROM File f " +
            "JOIN f.user u " +
            "WHERE u.email = :email " +
            "OR u IN (SELECT s FROM User u2 " +
            "JOIN u2.subscriptions s " +
            "WHERE u2.email = :email)")

    List<File> findAllFilesByUserFollowing(@Param("email") String email);

}
