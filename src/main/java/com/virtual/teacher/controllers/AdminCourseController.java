package com.virtual.teacher.controllers;

import com.virtual.teacher.models.Course;
import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.CourseService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminCourseController {
    private final CourseService courseService;
    private final UserService userService;
    private final ContractService contractService;


    @GetMapping("/adminCourses")
    public String showAdminPanel(Model model) {
        List<Course> listCourses = courseService.getAll();
        User user = contractService.currentUser();
        if (user == null) {
            model.addAttribute("exceptionMessage", "Forbidden!");
            return "error";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("path", "/photos/" + userService.getLastPhoto(user).getFileName());
        model.addAttribute("listCourses", listCourses);
        return "adminCourses";
    }

    @RequestMapping("/delete/{id}")
    public String approveTeacherPromotion(@PathVariable(name = "id") long id) {

        try {
            courseService.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminCourses";

    }


}
