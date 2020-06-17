package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.AssignmentTask;
import com.virtual.teacher.models.Course;
import com.virtual.teacher.models.Lecture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface for video in lecture operations on a service
 *
 */

public interface LectureService {

    /**
     * Returns a reference to the lecture with the given identifier.
     *
     * @param id must be valid decimal number.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException if entity is not found
     */
    Lecture getById(long id);

    /**
     * Returns all instances of type Lecture.
     *
     * @return all entities
     */
    List<Lecture> getAll();


    /**
     * Saves a given lecture with the attached task file in a given course.
     *
     * @param course must not be {@literal null}.
     * @param lecture must not be {@literal null}.
     * @param taskFile must not be {@literal null}.
     */
    void create(Course course, Lecture lecture, MultipartFile taskFile);

    /**
     * Prepare saving a given file.
     *
     * @param lecture must not be {@literal null}.
     * @param imageFile must not be {@literal null}.
     * @param assignmentTask must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if lecture is null or empty,
     *                                  if imageFile doesn't match required format,
     *                                  if assignmentTask is null or empty,
     *                                  if Files,Paths of getBytes throw IOException
     */
    AssignmentTask saveAssignmentTask(Lecture lecture, MultipartFile imageFile, AssignmentTask assignmentTask);
}