package kg.attractor.microgram.controller;

import jakarta.validation.Valid;
import kg.attractor.microgram.dto.FileDto;
import kg.attractor.microgram.service.UserService;
import kg.attractor.microgram.service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {
    private final UserService userService;
    private final FileServiceImpl fileService;

    @GetMapping("{fileName}")
    public ResponseEntity<?> findByName(@PathVariable String fileName){
        return fileService.findByName(fileName);
    }

    @GetMapping("upload")
    public String upload(Authentication auth, Model model){
        model.addAttribute("fileDto", new FileDto());
        model.addAttribute("user", auth.getName());
        System.out.println("Model: " + model.asMap());

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
