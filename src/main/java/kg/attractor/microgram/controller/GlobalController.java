package kg.attractor.microgram.controller;

import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalController {
    private final UserService userService;

//    @ModelAttribute
//    public void addGlobalAttributes(Authentication auth, Model model) {
//        UserDto userDto = userService.findByEmail(auth.getName());
//        model.addAttribute("userDto", userDto);
//
//    }
}
