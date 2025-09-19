package kg.attractor.microgram.service;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.SuchEmailAlreadyExistsException;

import javax.management.relation.RoleNotFoundException;

public interface UserService {
    void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException, RoleNotFoundException;
}
