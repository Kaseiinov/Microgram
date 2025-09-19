package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.SuchEmailAlreadyExistsException;
import kg.attractor.microgram.model.User;

import javax.management.relation.RoleNotFoundException;

public interface UserService {
    UserDto findByEmail(String email);

    User findByEmailModel(String email);

    void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException, RoleNotFoundException;
}
