package com.virtual.teacher.controllers;

import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EnrolledCreatedController {
    private final ContractService contractService;
    private final UserService userService;

    @GetMapping("/myCoursesView")
    public String showCoursesPage(Model model, @PageableDefault(sort = "title", size = 6) Pageable pageable) {
        try {
            User user = contractService.currentUser();
            model.addAttribute("loggedUser", user);
            model.addAttribute("path", "/photos/"
                    + userService.getLastPhoto(user).getFileName());
            model.addAttribute("enrolledCourses", user.getEnrolledCourses());
            model.addAttribute("completedCourses", user.getCompletedCourses());
            model.addAttribute("createdCourses", user.getCreatedCourses());
        } catch (
                IllegalArgumentException e) {
            model.addAttribute("exceptionMessage", e.getMessage());
            return "error";
        }
        CoursesController.sortingAndPagingAttributes(model, pageable);
        return "myCoursesView";
    }

}
