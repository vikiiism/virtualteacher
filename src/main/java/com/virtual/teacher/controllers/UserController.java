package com.virtual.teacher.controllers;

import com.virtual.teacher.models.DTOs.UserEditDTO;
import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final ContractService contractService;

    @RequestMapping("/edit")
    public ModelAndView showEditUserPage() {
        ModelAndView mav = new ModelAndView("profile");
        User user;
        try {
            user = userService.findById(contractService.currentUser().getId());
            mav.addObject("loggedUser", user);
            mav.addObject("path", "/photos/"
                    + userService.getLastPhoto(user));
        } catch (IllegalArgumentException e) {
            mav.addObject("exceptionMessage", e.getMessage());
            mav.setViewName("error");
            return mav;
        }
        mav.addObject("allCourses", user.getEnrolledCourses());
        mav.addObject("createdCourses", user.getCreatedCourses());
        mav.addObject("user", new UserEditDTO(user));
        mav.addObject("path", "/photos/" + userService.getLastPhoto(user).getFileName());

        return mav;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") UserEditDTO user, BindingResult bindingResult,
                           @RequestParam(value = "photo", required = false) MultipartFile file,
                           RedirectAttributes modelAndView) {


        if (bindingResult.hasErrors()) {
            StringBuilder result = new StringBuilder("Wrong input in fields: ");
            bindingResult.getFieldErrors().forEach(error -> result.append(error.getDefaultMessage()).append(" "));
            modelAndView.addFlashAttribute("exceptionMessage", result.toString());
            return "redirect:/edit";
        }
        try {
            user.setId(contractService.currentUser().getId());
            modelAndView.addFlashAttribute("path", "/photos/"
                    + userService.getLastPhoto(contractService.currentUser()));
            userService.edit(user, file);
        } catch (IllegalArgumentException e) {
            modelAndView.addFlashAttribute("exceptionMessage", e.getMessage());
        }
        return "redirect:/edit";
    }

}