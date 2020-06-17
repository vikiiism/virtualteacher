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
public class AdminApproveController {

    private final UserService userService;
    private final ContractService contractService;

    @GetMapping("/approve")
    public String showApproveRequests(Model model) {
        List<User> listUsers = userService.findByHasRequested(true);
        if (fillUsers(model, listUsers, contractService, userService)) {
            return "error";
        }

        return "approve";
    }


    @RequestMapping("/hasRequestTrue/{id}")
    public String requestTeacher(@PathVariable(name = "id") long id) {
        try {
            userService.hasRequestedTrue(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/";
    }

    @RequestMapping("/approveTeacherPromotion/{id}")
    public String approveTeacherPromotion(@PathVariable(name = "id") long id) {
        try {
            userService.hasRequestedFalse(id);
            userService.promoteUserToTeacherById(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/approve";
    }

    @RequestMapping("/declineTeacherPromotion/{id}")
    public String declineTeacherPromotion(@PathVariable(name = "id") long id) {
        try {
            userService.hasRequestedFalse(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return "redirect:/approve";
    }

    static boolean fillUsers(Model model, List<User> listUsers, ContractService contractService, UserService userService) {
        User user = contractService.currentUser();
        if (user == null) {
            model.addAttribute("exceptionMessage", "Forbidden!");
            return true;
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("path", "/photos/" + userService.getLastPhoto(user).getFileName());
        model.addAttribute("listUsers", listUsers);
        return false;
    }
}
