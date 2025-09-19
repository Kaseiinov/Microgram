package kg.attractor.microgram.repository;

import kg.attractor.microgram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String username);

    List<User> findAllByEmailContainingIgnoreCase(String email);
}
