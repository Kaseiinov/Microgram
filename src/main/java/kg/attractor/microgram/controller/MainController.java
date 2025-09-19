package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.CommentDto;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.FileService;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final FileService fileService;
    private final CommentService commentService;
    private final LikeService likeService;
    private final UserService userService;

    @GetMapping
    public String mainPage(Model model){
        model.addAttribute("commentDto", new CommentDto());
        model.addAttribute("files", fileService.findAllFiles());
        return "index";
    }

//    @PostMapping
//    public String mainPage(@Valid CommentDto commentDto, Model model){}
}
