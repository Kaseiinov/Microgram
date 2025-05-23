package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.FileService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileService fileService;

    @GetMapping("profile")
    public String profile(Authentication auth, Model model){
        String userName = auth.getName();
        User user = userService.findByEmailModel(userName);
        UserDto userDto = userService.findByEmail(userName);
        model.addAttribute("userDto", userDto);
        model.addAttribute("posts", fileService.findAllFilesByUser(userName).size());
        model.addAttribute("followers", user.getFollowers().size());
        model.addAttribute("following", user.getSubscriptions().size());
        System.out.println(fileService.findAllFilesByUser(userName).size());
        return "user/profile";
    }
}
