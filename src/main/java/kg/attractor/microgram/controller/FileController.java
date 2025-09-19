package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.*;
import kg.attractor.microgram.model.Comment;
import kg.attractor.microgram.model.File;
import kg.attractor.microgram.service.CommentService;
import kg.attractor.microgram.service.LikeService;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {
    private final UserService userService;
    private final FileServiceImpl fileService;
    private final CommentService commentService;
    private final LikeService likeService;

    @PostMapping("/upload/comment/{fileName}")
    public String uploadComment(@Valid CommentDto commentDto, @PathVariable String fileName, Authentication auth, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            FileDto file = fileService.findByNameDto(fileName);
            UserDto user = userService.findByEmail(auth.getName());
            commentDto.setUserDto(user);
            commentDto.setFileDto(file);
            commentService.save(commentDto);
            return "redirect:/";
        }

        model.addAttribute("files", fileService.findAllFiles());
        model.addAttribute("commentDto", commentDto);
        return "index";

    }

    @GetMapping("getComments")
    public ResponseEntity<?> getComments(@RequestParam String fileName){
        File file = fileService.findByNameModel(fileName);
        System.out.println(file.getComments().size());

        List<CommentWithUserDto> comments = file.getComments().stream()
                .map(c -> new CommentWithUserDto(
                        c.getUser().getEmail(),
                        c.getComment()
                ))
                .toList();

        return ResponseEntity.ok(Map.of("comments", comments));
    }

    @GetMapping("like/count")
//    @ResponseBody
    public ResponseEntity<?> getCountOfLikes(@RequestParam String fileName){
        File file = fileService.findByNameModel(fileName);
        int likeCount = (file != null && file.getLikes() != null) ? file.getLikes().size() : 0;

        Map<String, Integer> response = new HashMap<>();
        response.put("likes", likeCount);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload/like/{fileName}")
    public String uploadLike(LikeDto likeDto, @PathVariable String fileName, Authentication auth, Model model){
        FileDto file = fileService.findByNameDto(fileName);
        UserDto user = userService.findByEmail(auth.getName());
        likeDto.setUserDto(user);
        likeDto.setFileDto(file);
        likeService.saveOrDeleteLike(likeDto);
        return "redirect:/";

    }



    @GetMapping("get/{fileName}")
    public ResponseEntity<?> findByName(@PathVariable String fileName){
        return fileService.findByName(fileName);
    }

    @GetMapping("upload")
    public String upload(Authentication auth, Model model){
        model.addAttribute("fileDto", new FileDto());
        model.addAttribute("user", auth.getName());

        return "file/uploadFile";
    }

    @PostMapping("upload")
    public String upload(@Valid FileDto fileDto, Authentication auth, BindingResult bindingResult, Model model){

        if (fileDto.getMultipartFile() == null || fileDto.getMultipartFile().isEmpty()) {
            bindingResult.rejectValue("multipartFile", "error.multipartFile", "File cannot be empty");
        }

        if(!bindingResult.hasErrors()){
            fileService.uploadFile(fileDto);
            return "redirect:/user/profile";
        }

        model.addAttribute("fileDto", fileDto);
        model.addAttribute("user", auth.getName());
        return "file/uploadFile";
    }
}
