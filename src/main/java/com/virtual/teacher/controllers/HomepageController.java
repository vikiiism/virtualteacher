package com.virtual.teacher.controllers;

import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.CourseService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HomepageController {
    private final CourseService courseService;
    private final UserService userService;
    private final ContractService contractService;

    @GetMapping("/index")
    public String showHomePage(Model model) {
        return displayHomePage(model);
    }

    @GetMapping("/")
    public String showHomePageSecond(Model model) {
        return displayHomePage(model);
    }

    private String displayHomePage(Model model) {
        try {
            User loggedUser = contractService.currentUser();
            if (loggedUser != null) {
                model.addAttribute("loggedUser", loggedUser);
                model.addAttribute("path", "/photos/"
                        + userService.getLastPhoto(loggedUser).getFileName());
            }
        } catch (IllegalArgumentException e) {
            model.addAttribute("exceptionMessage", e.getMessage());
        }
        model.addAttribute("allCourses", courseService.getTopRated());
        return "index";
    }
}
