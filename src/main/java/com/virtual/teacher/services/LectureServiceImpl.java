package com.virtual.teacher.services;

import com.virtual.teacher.models.AssignmentTask;
import com.virtual.teacher.models.Course;
import com.virtual.teacher.models.Lecture;
import com.virtual.teacher.models.LectureVideo;
import com.virtual.teacher.repositories.LectureRepository;
import com.virtual.teacher.services.interfaces.AssignmentTaskService;
import com.virtual.teacher.services.interfaces.LectureService;
import com.virtual.teacher.services.interfaces.LectureVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final AssignmentTaskService assignmentTaskService;
    private final LectureVideoService lectureVideoService;

    @Override
    public Lecture getById(long id) {
        Lecture lecture = lectureRepository.getOne(id);
        if (lecture == null) {
            throw new IllegalArgumentException(String.format("Lecture with id %s not exist.", id));
        }
        return lecture;
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @Override
    public void create(Course course, Lecture lecture, MultipartFile taskFile) {
        lecture.setCourse(course);
        lecture.setAssignmentTask(saveAssignmentTask(lecture, taskFile, new AssignmentTask()));
        LectureVideo lectureVideo = lectureVideoService.save(new LectureVideo(lecture.getVideo().getTitle()));
        lecture.setVideo(lectureVideo);
        lectureRepository.save(lecture);
    }


    @Override
    @Transactional
    public AssignmentTask saveAssignmentTask(Lecture lecture, MultipartFile taskFile, AssignmentTask assignmentTask) {
        assignmentTask.setLecture(lecture);
        return assignmentTaskService.saveAssignmentTask(assignmentTask, taskFile);
    }

}
