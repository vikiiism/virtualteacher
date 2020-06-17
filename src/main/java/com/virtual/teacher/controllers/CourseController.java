package com.virtual.teacher.controllers;

import com.virtual.teacher.models.*;
import com.virtual.teacher.models.DTOs.CourseDTO;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;
    private final TopicService topicService;
    private final AssignmentSolutionService assignmentSolutionService;
    private final LectureVideoService lectureVideoService;
    private final ContractService contractService;

    @GetMapping("/courses/{id}")
    public String showCoursePage(@PathVariable long id, Model model) {
        Course course = getCourse(id);
        try {
            model.addAttribute("pathCoursePhoto",
                    "../coursePhotos/" + courseService.getLastPhoto(course).getFileName());
            User user = contractService.currentUser();
            if (user != null) {
                model.addAttribute("loggedUser", user);
                model.addAttribute("path",
                        "/photos/" + userService.getLastPhoto(user).getFileName());
                model.addAttribute("allWatchedVideos", user.getVideosWatched());
                model.addAttribute("allSolutions", userService.findHomeworksInCourse(user.getId(), id));
            }
        } catch (Exception e) {
            model.addAttribute("exceptionMessage", e.getMessage());
            return "error";
        }
        List<AssignmentSolution> assignmentSolutions = new ArrayList<>();
        List<Lecture> lectures = course.getLectures();
        lectures.forEach(lecture -> assignmentSolutions.addAll(lecture.getAssignmentSolutions()));
        model.addAttribute("solutions", assignmentSolutions);
        model.addAttribute("course", course);
        model.addAttribute("lectures", course.getLectures());
        return "course";
    }

    @GetMapping("/createCourse")
    public String showCreateCoursePage(Model model) {
        User user = contractService.currentUser();
        if (user == null) {
            model.addAttribute("exceptionMessage", "Forbidden!");
            return "error";
        }
        model.addAttribute("loggedUser", user);
        model.addAttribute("path", "/photos/" + userService.getLastPhoto(user).getFileName());
        model.addAttribute("course", new CourseDTO());
        model.addAttribute("topics", topicService.getAll());
        return "createCourse";
    }

    @RequestMapping(value = "/createCourse", method = RequestMethod.POST)
    public String createCourse(@Valid @ModelAttribute CourseDTO courseDTO, BindingResult bindingResult,
                               @RequestParam(name = "topic") Topic topic, @RequestParam("photo") MultipartFile file,
                               RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            StringBuilder result = new StringBuilder("Wrong input in fields: ");
            bindingResult.getFieldErrors().forEach(error -> result.append(error.getDefaultMessage())
                    .append("  "));
            attributes.addFlashAttribute("exceptionMessage", result.toString());
            return "redirect:/createCourse";
        }
        try {
            Course course = courseService.create(courseDTO, topic, file, contractService.currentUser());

            return "redirect:/editCourse/" + course.getId();
        } catch (Exception e) {
            attributes.addFlashAttribute("exceptionMessage", "Wrong file type!");
            return "redirect:/createCourse";
        }
    }


    @RequestMapping("/editCourse/{id}")
    public String editCoursePage(@PathVariable long id, Model model) {
        Course course = getCourse(id);
        User currentUser = contractService.currentUser();
        if (currentUser != null) {
            model.addAttribute("loggedUser", currentUser);
            model.addAttribute("path", "/photos/"
                    + userService.getLastPhoto(currentUser).getFileName());
        }
        model.addAttribute("course", course);
        model.addAttribute("pathCoursePhoto",
                "../coursePhotos/" + courseService.getLastPhoto(course).getFileName());
        model.addAttribute("lectures", course.getLectures());
        return "editCourse";
    }

    @RequestMapping(value = "/{id}/saveCourse", method = RequestMethod.PUT, params = "coursesave")
    public String saveCourse(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Course course = getCourse(id);
        try {
            course.setEnabled(true);
            courseService.save(course);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("exceptionMessage", e.getMessage());
            return "redirect:/editCourse";
        }
        return "redirect:/courses/" + id;
    }


    @RequestMapping(value = "/courses/{id}", method = RequestMethod.PUT)
    public String enrollCourse(@PathVariable long id) {
        try {
            userService.enrollCourse(contractService.currentUser().getId(), id);
        } catch (IllegalArgumentException e) {
            return "courses/{id}";
        }
        return "redirect:/courses/{id}";

    }

    @RequestMapping(value = "/courses/{id}", method = RequestMethod.POST)
    public String rateCourse(@PathVariable long id, String action, RedirectAttributes model) {

        try {
            courseService.rate(contractService.currentUser().getId(), id, Integer.parseInt(action));
        } catch (Exception e) {
            model.addFlashAttribute("exceptionMessage", e.getMessage());
        }

        return "redirect:/courses/{id}";
    }

    @GetMapping("/courses/{id}/videoLecture/{videoId}")
    public String showVideoLecture(@PathVariable("id") long id, @PathVariable("videoId") Long videoId, Model model) {

        Course course;
        LectureVideo lectureVideo;
        try {
            course = getCourse(id);
            lectureVideo = lectureVideoService.getById(videoId);
            User user = contractService.currentUser();
            if (user != null) {
                userService.isWatched(user.getId(), lectureVideo.getId());
                model.addAttribute("loggedUser", user);
                model.addAttribute("path", "/photos/"
                        + userService.getLastPhoto(user).getFileName());
            }
            model.addAttribute("pathCoursePhoto",
                    "../coursePhotos/" + courseService.getLastPhoto(course).getFileName());
        } catch (Exception e) {
            model.addAttribute("exceptionMessage", e.getMessage());
            return "error";
        }
        model.addAttribute("course", course);
        model.addAttribute("lectures", course.getLectures());
        model.addAttribute("video", lectureVideo);
        return "videoLecture";
    }

    @RequestMapping(value = "/courses/{id}/gradeHomework/{solutionId}", method = RequestMethod.POST)
    public String gradeHomework(@PathVariable("id") long id, @PathVariable("solutionId") long solutionId,
                                @RequestParam("grade") String grade, RedirectAttributes model) {
        try {
            assignmentSolutionService.gradeAssignmentSolution(solutionId, grade);
        } catch (IllegalArgumentException e) {
            model.addFlashAttribute("exceptionMessage", e.getMessage());
        }
        return "redirect:/courses/" + id;
    }

    Course getCourse(long id) {
        return courseService.getById(id);
    }

}
