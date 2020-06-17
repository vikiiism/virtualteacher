package com.virtual.teacher.services;

import com.virtual.teacher.models.AssignmentTask;
import com.virtual.teacher.repositories.AssignmentTaskRepository;
import com.virtual.teacher.services.interfaces.AssignmentTaskService;
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
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssignmentTaskServiceImpl implements AssignmentTaskService {

    private final AssignmentTaskRepository assignmentTaskRepository;

    private static String UPLOADED_FOLDER = System.getProperty("user.dir") + File.separator
            + "courseTasks" + File.separator;


    @Override
    public AssignmentTask saveAssignmentTask(AssignmentTask assignmentTask, MultipartFile taskFile) {
        if (!taskFile.getOriginalFilename().matches("([^\\s]+(\\.(?i)(pdf|txt))$)")) {
            throw new IllegalArgumentException(String.format("Filetype is invalid", taskFile));
        }
        assignmentTask.setPath(UPLOADED_FOLDER);
        assignmentTask.setFileName(UPLOADED_FOLDER);
        try {
            return writeFile(assignmentTask, taskFile);
        } catch (IOException e) {
            throw new IllegalArgumentException("File cannot be stored, try again!");
        }
    }


    @Override
    public AssignmentTask save(AssignmentTask assignmentTask) {
        return assignmentTaskRepository.save(assignmentTask);
    }

    @Override
    public AssignmentTask findById(long id) {

        Optional<AssignmentTask> assignmentTask = assignmentTaskRepository.findById(id);

        if (!assignmentTask.isPresent()) {
            throw new IllegalArgumentException(String.format("Assignment with id %s was not found!", id));
        }
        return assignmentTask.get();
    }

    private AssignmentTask writeFile(AssignmentTask assignmentTask, MultipartFile taskFile) throws IOException {
        byte[] bytes = taskFile.getBytes();
        String fileName = taskFile.getOriginalFilename();
        assignmentTask.setFileName(assignmentTask.getLecture().getTitle() + fileName);
        Path path = Paths.get(UPLOADED_FOLDER + assignmentTask.getLecture().getTitle() + fileName);
        Files.write(path, bytes);
        return save(assignmentTask);
    }
}
