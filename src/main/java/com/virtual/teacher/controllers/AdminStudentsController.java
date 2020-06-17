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
public class AdminStudentsController {
    private final UserService userService;
    private final ContractService contractService;

    @GetMapping("/adminStudents")
    public String showAdminStudents(Model model) {
        List<User> listUsers = userService.findAllStudents();
        if (AdminApproveController.fillUsers(model, listUsers, contractService, userService)){
            return "error";
        }

        return "adminStudents";
    }

    @RequestMapping("/deleteStudent/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        try {
            userService.softDeleteUserById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminStudents";
    }

    @RequestMapping("/restoreStudent/{id}")
    public String restoreUser(@PathVariable(name = "id") long id) {
        try {
            userService.restoreDeletedAccountById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminStudents";
    }

    @RequestMapping("/promoteStudent/{id}")
    public String promoteStudentToTeacher(@PathVariable(name = "id") long id) {
        try {
            userService.promoteUserToTeacherById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminStudents";
    }

    @RequestMapping("/promoteStudentAdmin/{id}")
    public String promoteStudentToAdmin(@PathVariable(name = "id") long id) {
        try {
            userService.promoteUserToAdminById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminStudents";
    }

}
