package com.virtual.teacher.controllers;

import com.virtual.teacher.models.DTOs.UserRegistrationDto;
import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminAdminsController {

    private final UserService userService;
    private final ContractService contractService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/createAdmin")
    public String showRegistrationPage(Model model) {

        User user = contractService.currentUser();
        if (user == null) {
            model.addAttribute("exceptionMessage", "Forbidden!");
            return "error";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("path", "/photos/" +
                userService.getLastPhoto(user).getFileName());

        return "createAdmin";
    }

    @PostMapping("/createAdmin")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult,
                               @RequestParam("photo") MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("exceptionMessage", "Incorrect field/fields! ");
            return "createAdmin";
        }
        try {
            userService.saveAdmin(userRegistrationDto, file);
        } catch (IllegalArgumentException e) {
            model.addAttribute("exceptionMessage", e.getMessage());
            return "createAdmin";
        }

        return "redirect:/adminAdmins";
    }

    @GetMapping("/adminAdmins")
    public String showAdminAdmins(Model model) {
        List<User> listUsers = userService.findAllAdmins();
        if (AdminApproveController.fillUsers(model, listUsers, contractService, userService)) {
            return "error";
        }

        return "adminAdmins";
    }

    @RequestMapping("/deleteAdmin/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        try {
            userService.softDeleteUserById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminAdmins";
    }

    @RequestMapping("/restoreAdmin/{id}")
    public String restoreUser(@PathVariable(name = "id") long id) {
        try {
            userService.restoreDeletedAccountById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminAdmins";
    }

    @RequestMapping("/demoteAdminStudent/{id}")
    public String promoteStudentToTeacher(@PathVariable(name = "id") long id) {
        try {
            userService.demoteTeacherToUserById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminAdmins";
    }

    @RequestMapping("/demoteAdminTeacher/{id}")
    public String promoteStudentToAdmin(@PathVariable(name = "id") long id) {
        try {
            userService.promoteUserToTeacherById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/adminAdmins";
    }

}
