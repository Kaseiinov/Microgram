package kg.attractor.microgram.service.impl;

import jakarta.transaction.Transactional;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.dto.UserImageDto;
import kg.attractor.microgram.exceptions.SuchEmailAlreadyExistsException;
import kg.attractor.microgram.exceptions.UserNotFoundException;
import kg.attractor.microgram.model.Role;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.model.UserImage;
import kg.attractor.microgram.repository.UserRepository;
import kg.attractor.microgram.service.RoleService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.utils.FileUtil;
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
    @Transactional
    public void follow(String followTo, String follower){
        User followToUser = userRepository.findByEmail(followTo).orElseThrow(UserNotFoundException::new);
        User followerUser = userRepository.findByEmail(follower).orElseThrow(UserNotFoundException::new);

        followerUser.follow(followToUser);
//        userRepository.save(followToUser);
        userRepository.save(followerUser);
    }

    @Override
    public List<UserDto> findAllUsersLike(String name) {
        List<User> users = userRepository.findAllByEmailContainingIgnoreCase(name);

        return users.stream()
                .map(user -> UserDto.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .avatar(user.getAvatar() != null ?
                                UserImageDto.builder()
                                        .id(user.getAvatar().getId())
                                        .fileName(user.getAvatar().getFileName())
                                        .userId(user.getId())
                                        .build()
                                : null)
                        .build())
                .toList();
    }


    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        UserImageDto avatar = null;
        if (user.getAvatar() != null) {
            avatar = UserImageDto.builder()
                    .id(user.getAvatar().getId())
                    .fileName(user.getAvatar().getFileName())
                    .userId(user.getId())
                    .build();
        }

        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatar(avatar)
                .build();
    }


    @Override
    public User findByEmailModel(String email){
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

    }

    @Override
    public void addUser(UserDto userDto) throws SuchEmailAlreadyExistsException{
        FileUtil fileUtil = new FileUtil();
        String filename = fileUtil.saveUploadFile(userDto.getAvatar().getFile(), "images/");

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

        UserImage userImage = new UserImage();
        userImage.setUser(user);
        userImage.setFileName(filename);

        user.setAvatar(userImage);

        userRepository.saveAndFlush(user);
    }
}
