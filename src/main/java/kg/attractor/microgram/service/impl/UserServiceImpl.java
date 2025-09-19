package kg.attractor.microgram.service.impl;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.exceptions.SuchEmailAlreadyExistsException;
import kg.attractor.microgram.exceptions.UserNotFoundException;
import kg.attractor.microgram.model.Role;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.repository.UserRepository;
import kg.attractor.microgram.service.RoleService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder encoder;

    @Override
    public UserDto findByEmail(String email){
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .build();
    }

    @Override
    public User findByEmailModel(String email){
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

    }

    @Override
    public void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException{

        boolean isUserExists = userRepository.existsUserByEmail(userDto.getEmail());
        if(isUserExists){
            throw new SuchEmailAlreadyExistsException();
        }

        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEnabled(true);

        Role role = roleService.findRoleByRole("USER");

        user.setRoles(Collections.singletonList(role));
        role.setUsers(List.of(user));

        userRepository.saveAndFlush(user);
    }
}
