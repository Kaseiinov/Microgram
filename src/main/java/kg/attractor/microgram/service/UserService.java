package kg.attractor.microgram.service;

import jakarta.transaction.Transactional;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.SuchEmailAlreadyExistsException;
import kg.attractor.microgram.model.User;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {
    void follow(String followTo, String follower);

    @Transactional
    void unFollow(String followTo, String follower);

    List<UserDto> findAllUsersLike(String name);

    UserDto findByEmail(String email);

    User findByEmailModel(String email);

    void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException, RoleNotFoundException;
}
