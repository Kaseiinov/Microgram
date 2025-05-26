package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.FileService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FileService fileService;

    @GetMapping("profile")
    public String profile(@RequestParam String name, Authentication auth, Model model){
        String userName;
        if(name != null && !name.isEmpty()){
            userName = name;
        } else{
            userName = auth.getName();
        }
        User user = userService.findByEmailModel(userName);
        UserDto userDto = userService.findByEmail(userName);
        model.addAttribute("isCurrent", true);
        model.addAttribute("userDto", userDto);
        model.addAttribute("posts", fileService.findAllFilesByUser(userName).size());
        model.addAttribute("followers", user.getFollowers().size());
        model.addAttribute("following", user.getSubscriptions().size());
        model.addAttribute("files", fileService.findAllFilesByUser(userName));
        model.addAttribute("commentDto", new CommentDto());
        System.out.println(fileService.findAllFilesByUser(userName).size());
        return "user/profile";
    }

    @GetMapping("search")
    public String search(@RequestParam(defaultValue = "") String name, Model model){
        List<UserDto> userDto = userService.findAllUsersLike(name);
        model.addAttribute("users", userDto);
        return "user/searchUser";
    }

    @GetMapping("searched")
    public String anotherProfile(@RequestParam String name, Model model){

        User user = userService.findByEmailModel(name);
        UserDto userDto = userService.findByEmail(name);

        model.addAttribute("isCurrent", false);
        model.addAttribute("userDto", userDto);
        model.addAttribute("posts", fileService.findAllFilesByUser(name).size());
        model.addAttribute("followers", user.getFollowers().size());
        model.addAttribute("following", user.getSubscriptions().size());
        model.addAttribute("files", fileService.findAllFilesByUser(name));
        model.addAttribute("commentDto", new CommentDto());
        System.out.println(fileService.findAllFilesByUser(name).size());
        return "user/profile";
    }

    @PostMapping("follow")
    public String follow(@RequestParam String follow, Authentication auth){
        userService.follow(follow, auth.getName());
        return "redirect:/user/profile?name=" + follow;
    }

}
