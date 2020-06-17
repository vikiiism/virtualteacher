package com.virtual.teacher.controllers;

import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.CourseService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoursesController {
    private final CourseService courseService;
    private final UserService userService;
    private final ContractService contractService;

    @GetMapping("/courses")
    public String showCoursesPage(@RequestParam(value = "search", required = false) String search, Model model,
                                  @PageableDefault(sort = "title", size = 6) Pageable pageable,
                                  @RequestParam(name = "topic", required = false) String topic,
                                  @RequestParam(name = "author", required = false) String author) {
        try {
            User loggedUser = contractService.currentUser();
            if (loggedUser != null) {
                model.addAttribute("loggedUser", loggedUser);
                model.addAttribute("path", "/photos/"
                        + userService.getLastPhoto(loggedUser).getFileName());
            }
            if (topic != null || author != null) {
                model.addAttribute("allCourses", courseService.findAllFiltered(topic, author, pageable));
            } else if (search != null && !search.isEmpty()) {
                model.addAttribute("allCourses", courseService.findAllBySearch(search, pageable));
            } else {
                model.addAttribute("allCourses", courseService.findCourses(pageable));
            }

        } catch (IllegalArgumentException e) {
            model.addAttribute("exceptionMessage", e.getMessage());
            return "error";
        }
        return addCurrentPageAndSort(model, pageable);
    }

    private String addCurrentPageAndSort(Model model, @PageableDefault(sort = "title", size = 6) Pageable pageable) {
        sortingAndPagingAttributes(model, pageable);
        return "courses";
    }

    static void sortingAndPagingAttributes(Model model, @PageableDefault(sort = "title", size = 6) Pageable pageable) {
        model.addAttribute("currentPage", pageable.getPageNumber());
        List<Sort.Order> sortOrders = pageable.getSort().stream().collect(Collectors.toList());
        if (sortOrders.size() > 0) {
            Sort.Order order = sortOrders.get(0);
            model.addAttribute("sortBy", order.getProperty());
            model.addAttribute("sortDesc", order.getDirection() == Sort.Direction.DESC);
        }
    }
}
