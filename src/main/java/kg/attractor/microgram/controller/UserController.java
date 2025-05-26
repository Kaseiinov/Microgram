package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.model.User;
import kg.attractor.microgram.service.FileService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
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
    public String profile(@RequestParam(defaultValue = "") String name, Authentication auth, Model model){
        String userName;
        if(name != null && !name.isEmpty()){
            userName = name;
        } else{
            userName = auth.getName();
        }
        User user = userService.findByEmailModel(userName);
        UserDto userDto = userService.findByEmail(userName);
        prepareModelForProfile(user, true, false, model);
        return "user/profile";
    }

    @GetMapping("search")
    public String search(@RequestParam(defaultValue = "") String name, Model model){
        List<UserDto> userDto = userService.findAllUsersLike(name);
        model.addAttribute("users", userDto);
        return "user/searchUser";
    }

    @GetMapping("searched")
    public String anotherProfile(@RequestParam String name, Authentication auth, Model model){
        boolean isCurrent = false;
        boolean isFollowed = false;

        User follower = userService.findByEmailModel(auth.getName());
        User user = userService.findByEmailModel(name);
        UserDto userDto = userService.findByEmail(name);

        if(auth.getName() != null && !auth.getName().isEmpty()){
            if(auth.getName().equals(name)){
                isCurrent = true;
            }
            if(follower.isFollowing(user)){
                isFollowed = true;
            }
        }

        prepareModelForProfile(user, isCurrent, isFollowed, model);
        return "user/profile";
    }

    @PostMapping("follow")
    public String follow(@RequestParam String follow, Authentication auth){
        userService.follow(follow, auth.getName());
        return "redirect:/user/profile?name=" + follow;
    }

    @PostMapping("unFollow")
    public String unFollow(@RequestParam String follow, Authentication auth){
        userService.unFollow(follow, auth.getName());
        return "redirect:/user/profile?name=" + follow;
    }

    private void prepareModelForProfile(User user, boolean isCurrent, boolean isFollowed, Model model) {
        UserDto userDto = userService.findByEmail(user.getEmail());
        model.addAttribute("isCurrent", isCurrent);
        model.addAttribute("isFollowed", isFollowed);
        model.addAttribute("userDto", userDto);
        model.addAttribute("posts", fileService.findAllFilesByUser(user.getEmail()).size());
        model.addAttribute("followers", user.getFollowers().size());
        model.addAttribute("following", user.getSubscriptions().size());
        model.addAttribute("files", fileService.findAllFilesByUser(user.getEmail()));
        model.addAttribute("commentDto", new CommentDto());
    }



}
