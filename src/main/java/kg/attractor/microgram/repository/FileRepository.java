package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findByFileName(String fileName);

    List<File> findAllFilesByUser_Email(String userEmail);
}
