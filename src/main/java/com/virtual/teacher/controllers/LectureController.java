package com.virtual.teacher.controllers;

import com.virtual.teacher.models.AssignmentSolution;
import com.virtual.teacher.models.AssignmentTask;
import com.virtual.teacher.models.Lecture;
import com.virtual.teacher.models.specifications.MediaTypeUtils;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.AssignmentSolutionService;
import com.virtual.teacher.services.interfaces.AssignmentTaskService;
import com.virtual.teacher.services.interfaces.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LectureController {
    private final ServletContext servletContext;
    private final LectureService lectureService;
    private final CourseController courseController;
    private final AssignmentTaskService assignmentService;
    private final AssignmentSolutionService solutionService;
    private final ContractService contractService;

    @GetMapping("/editCourse/{id}/addLecture")
    public String addNewLecture(@PathVariable long id, Model model) {

        model.addAttribute("course", courseController.getCourse(id));
        model.addAttribute("lecture", new Lecture());

        return "addLecture";
    }

    @RequestMapping(value = "/editCourse/{id}/addLecture", method = RequestMethod.POST)
    public String saveLecture(@PathVariable long id, @Valid @ModelAttribute("lecture") Lecture lecture,
                              BindingResult result,
                              @RequestParam("task") MultipartFile file, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder("Wrong input in fields: ");
            result.getFieldErrors().forEach(error -> stringBuilder.append(error.getField()).append(" "));
            attributes.addFlashAttribute("exceptionMessage", stringBuilder.toString());
            return "redirect:/editCourse/" + id + "/addLecture";
        }
        try {
            lectureService.create(courseController.getCourse(id), lecture, file);
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exceptionMessage", e.getMessage());
            return "redirect:/editCourse/" + id + "/addLecture";
        }

        return "redirect:/editCourse/{id}";
    }

    @RequestMapping(value = "/courses/{courseId}/uploadHomework/{id}", method = RequestMethod.POST)
    public String uploadHomeworkToLecture(@PathVariable long courseId,
                                          @RequestParam("solution") MultipartFile file,
                                          @PathVariable long id, RedirectAttributes attributes) {
        try {
            solutionService.saveAssignmentSolution(new AssignmentSolution(contractService.currentUser(),
                    lectureService.getById(id)), file);
        } catch (IllegalArgumentException e) {
            attributes.addFlashAttribute("exceptionMessage", e.getMessage());
        }
        return "redirect:/courses/" + courseId;
    }

    @RequestMapping(value = "/{id}/download", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadAssignment(
            @PathVariable long id) {
        try {
            AssignmentTask assignmentTask = assignmentService.findById(id);
            return download(assignmentTask.getPath(), assignmentTask.getFileName());
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");

        }
    }

    @RequestMapping(value = "/{id}/downloadSolution", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadAssignmentSolution(@PathVariable long id) {
        try {
            AssignmentSolution assignmentSolution = solutionService.findById(id);
            return download(assignmentSolution.getPath(), assignmentSolution.getFileName());
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found!");
        }
    }


    private ResponseEntity<InputStreamResource> download(String path, String fileName) throws FileNotFoundException {
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext,
                fileName);
        File file = new File(path + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(mediaType)
                .contentLength(file.length())
                .body(resource);
    }
}