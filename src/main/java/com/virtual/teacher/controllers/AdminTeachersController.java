package com.virtual.teacher.controllers;

import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
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
public class AdminTeachersController {

    private final UserService userService;
    private final ContractService contractService;

    @GetMapping("/adminTeachers")
    public String showAdminTeacher(Model model) {
        List<User> listTeachers = userService.findAllTeachers();
        User user = contractService.currentUser();
        if (user == null) {
            model.addAttribute("exceptionMessage", "Forbidden!");
            return "error";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("path", "/photos/" + userService.getLastPhoto(user).getFileName());
        model.addAttribute("listTeachers", listTeachers);
        return "adminTeachers";
    }

    @RequestMapping("/deleteTeacher/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        try {
            userService.softDeleteUserById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminTeachers";
    }

    @RequestMapping("/restoreTeacher/{id}")
    public String restoreUser(@PathVariable(name = "id") long id) {
        try {
            userService.restoreDeletedAccountById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminTeachers";
    }

    @RequestMapping("/demoteTeacher/{id}")
    public String promoteStudentToTeacher(@PathVariable(name = "id") long id) {
        try {
            userService.demoteTeacherToUserById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminTeachers";
    }

    @RequestMapping("/promoteTeacher/{id}")
    public String promoteTeacherToAdmin(@PathVariable(name = "id") long id) {
        try {
            userService.promoteUserToAdminById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminTeachers";
    }

}
