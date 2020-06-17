package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.*;
import com.virtual.teacher.models.Course;
import com.virtual.teacher.models.CoursePhoto;
import com.virtual.teacher.models.DTOs.CourseDTO;
import com.virtual.teacher.models.Topic;
import com.virtual.teacher.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * Interface for video in lecture operations on a service
 *
 */

public interface CourseService {

    /**
     * Returns a reference to the course with the given identifier.
     *
     * @param id must be valid decimal number.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException if entity is not found
     */
    Course getById(long id);

    /**
     * Get last photo from course's photo list.
     *
     * @param course must not be {@literal null}.
     * @return reference to course's last photo
     */
    CoursePhoto getLastPhoto(Course course);

    /**
     * Returns a reference to the deleted course with the given identifier.
     *
     * @param id must be valid decimal number.
     * @throws IllegalArgumentException if entity is not found
     */
    void deleteById(long id);

    /**
     * Saves a given course with the attached photo, topic and user.
     *
     * @param course must not be {@literal null}.
     * @param topic must not be {@literal null}.
     * @param file must not be {@literal null}.
     * @param user must not be {@literal null}.
     */
    Course create(CourseDTO course, Topic topic, MultipartFile file, User user);

    /**
     * Saves a given course.
     * Method can be used to change the existing photo.
     *
     * @param course must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    Course save(Course course);

    /**
     * Updates rating for a given course.
     *
     * @param userId must be valid user id.
     * @param courseId must be valid course id.
     * @param rating must be valid decimal number.
     * @return the saved entity with updated rating.
     */
    Course rate(Long userId, Long courseId, Integer rating);

    /**
     * Returns top six by descending rating instances of type Lecture.
     *
     * @return top six by descending rating instances of type Lecture.
     */

    List<Course> getTopRated();

    /**
     * Returns all instances of type Course.
     *
     * @return all entities
     */
    List<Course> getAll();

    /**
     * Returns a page of courses.
     *
     * @param pageable must not be {@literal null}.
     * @return courses on page.
     */
    Page<Course> findCourses(Pageable pageable);

    /**
     * Saves a given coursePhoto.
     *
     * @param course      must not be {@literal null}.
     * @param imageFile is photo file
     * @param coursePhoto     is reference to to be uploaded object
     * @return the saved entity will never be {@literal null}.
     */
    CoursePhoto saveImage(Course course, MultipartFile imageFile, CoursePhoto coursePhoto);


    /**
     * Returns a page of courses.
     *
     * @param search must not be {@literal null}
     * @param pageable must not be {@literal null}.
     * @return all course that contain the search in description, topic or title.
     */
    Page<Course> findAllBySearch(String search, Pageable pageable);


    /**
     * Returns a page of courses.
     *
     * @param topic must not be {@literal null}
     * @param author must not be {@literal null}
     * @param pageable must not be {@literal null}.
     * @return all course that contain the topic or the author.
     */
    Page<Course> findAllFiltered(String topic, String author, Pageable pageable);
}
