package com.virtual.teacher.services;

import com.virtual.teacher.models.AssignmentSolution;
import com.virtual.teacher.models.Course;
import com.virtual.teacher.models.User;
import com.virtual.teacher.repositories.AssignmentSolutionRepository;
import com.virtual.teacher.services.interfaces.AssignmentSolutionService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssignmentSolutionServiceImpl implements AssignmentSolutionService {

    private final AssignmentSolutionRepository assignmentSolutionRepository;
    private final UserService userService;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir") + File.separator
            + "courseSolutions" + File.separator;

    @Override
    @Transactional
    public AssignmentSolution saveAssignmentSolution(AssignmentSolution assignmentSolution, MultipartFile taskFile) {
        checkFile(taskFile);
        assignmentSolution.setPath(UPLOADED_FOLDER);
        assignmentSolution.setFileName(UPLOADED_FOLDER);
        try {
            return writeFile(assignmentSolution, taskFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("File cannot be stored, try again!");
        }
    }

    @Override
    public AssignmentSolution save(AssignmentSolution assignmentSolution) {
        return assignmentSolutionRepository.save(assignmentSolution);
    }

    @Override
    public AssignmentSolution findById(long id) {
        Optional<AssignmentSolution> solution = assignmentSolutionRepository.findById(id);
        if (!solution.isPresent()) {
            throw new IllegalArgumentException(String.format("Id %d not found.", id));
        }
        return solution.get();
    }

    @Override
    @Transactional
    public AssignmentSolution gradeAssignmentSolution(long id, String grade) {
        AssignmentSolution solution = findById(id);
        int solutionGrade = checkGradeValue(grade);
        solution.setGrade(solutionGrade);
        solution = assignmentSolutionRepository.save(solution);
        completeCourseCheck(solution.getUser(), solution.getLecture().getCourse());
        return solution;
    }


    @Override
    public List<AssignmentSolution> findAllAssignmentSolutionsByUserId(long userId) {
        return assignmentSolutionRepository.findAssignmentSolutionsByUserId(userId);
    }

    @Override
    public List<AssignmentSolution> findAssignmentSolutionByUserAndLecture(long userId, long lectureId) {
        return assignmentSolutionRepository.findAssignmentSolutionByUserAndLecture(userId, lectureId);
    }

    private AssignmentSolution writeFile(AssignmentSolution assignmentSolution, MultipartFile taskFile) throws IOException {
        byte[] bytes = taskFile.getBytes();
        String fileName = taskFile.getOriginalFilename();
        assignmentSolution.setFileName(assignmentSolution.getLecture().getTitle() + fileName);
        Path path = Paths.get(UPLOADED_FOLDER + assignmentSolution.getLecture().getTitle() + fileName);
        Files.write(path, bytes);
        AssignmentSolution saved = save(assignmentSolution);
        assignmentSolution.getUser().getSolutions().add(saved);
        saved.setUser(userService.update(saved.getUser()));
        return saved;
    }

    private void checkFile(MultipartFile taskFile) {
        if (taskFile == null || taskFile.isEmpty() || taskFile.getOriginalFilename() == null) {
            throw new IllegalArgumentException("Empty file cannot be stored! ");
        }
        if (!taskFile.getOriginalFilename().matches("([^\\s]+(\\.(?i)(pdf|txt))$)")) {
            throw new IllegalArgumentException("File type is invalid");
        }
    }

    private void completeCourseCheck(User user, Course course) {
        List<AssignmentSolution> solutions = userService
                .findHomeworksInCourse(user.getId(), course.getId());
        int sum = solutions.stream().mapToInt(AssignmentSolution::getGrade).sum();
        if ((double) sum / (course.getLectures().size()) >= course.getGrade()) {
            user.getEnrolledCourses().remove(course);
            user.getCompletedCourses().add(course);
            userService.update(user);
        }
    }

    private int checkGradeValue(String grade) {
        int solutionGrade;
        try {
            solutionGrade = Integer.parseInt(grade);
            if (solutionGrade > 100 || solutionGrade < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Wrong value for grade");
        }
        return solutionGrade;
    }

}
