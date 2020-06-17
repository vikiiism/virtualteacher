package com.virtual.teacher.controllers;

import com.virtual.teacher.models.DTOs.UserRegistrationDto;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {
    private final UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")) {
            return "redirect:/index";
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult,
                               @RequestParam("photo") MultipartFile file, Model model) {
        if (bindingResult.hasErrors()) {
            StringBuilder result = new StringBuilder("Wrong input in fields: ");
            bindingResult.getFieldErrors().forEach(error -> result.append(error.getDefaultMessage()).append(" "));
            model.addAttribute("exceptionMessage", result.toString());
            return "registration";
        }
        try {
            if (file.getSize() >= 10000000) {
                throw new IllegalArgumentException("Too big file! ");
            }
            userService.saveStudent(userRegistrationDto, file);
        } catch (IllegalArgumentException e) {
            model.addAttribute("exceptionMessage", e.getMessage());
            return "registration";
        }
        return "redirect:/login";
    }

}
