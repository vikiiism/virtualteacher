package com.virtual.teacher.services;

import com.virtual.teacher.models.*;
import com.virtual.teacher.models.DTOs.CourseDTO;
import com.virtual.teacher.models.specifications.CourseSpecification;
import com.virtual.teacher.services.interfaces.CoursePhotoService;
import com.virtual.teacher.services.interfaces.CourseService;
import com.virtual.teacher.repositories.CourseRatingRepository;
import com.virtual.teacher.repositories.CourseRepository;
import com.virtual.teacher.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final CourseRatingRepository courseRatingRepository;
    private final UserRepository userRepository;
    private final CoursePhotoService coursePhotoService;

    @Override
    public Course getById(long id) {
        Course course = courseRepository.getById(id);
        if (course == null) {
            throw new IllegalArgumentException(String.format("No course with id %d", id));
        }
        return course;
    }

    @Override
    public CoursePhoto getLastPhoto(Course course) {
        List<CoursePhoto> photos = getById(course.getId()).getCoursePhotos();
        if (photos == null || photos.isEmpty()) {
            return saveImage(course, null, new CoursePhoto());
        }
        return photos.get(photos.size() - 1);
    }

    @Override
    public void deleteById(long id) {
        Course course = getById(id);
        if (course == null) {
            throw new IllegalArgumentException(String.format("No course with id %d", id));
        }
        courseRepository.delete(course);
    }

    @Override
    public Page<Course> findAllBySearch(String search, Pageable pageable) {
        return courseRepository.findAll(CourseSpecification.textInAllColumns(search), pageable);
    }

    @Override
    public Page<Course> findAllFiltered(String topic, String author, Pageable pageable) {
        return courseRepository.findAll(CourseSpecification.filterByTopicAndAuthor(topic, author), pageable);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> findCourses(Pageable pageable) {
        return courseRepository.findAll(CourseSpecification.filterEnabled(), pageable);
    }

    @Override
    public List<Course> getTopRated() {
        return courseRepository.findTopSixRatedCourses();
    }

    @Override
    public Course create(CourseDTO courseDTO, Topic topic, MultipartFile file, User user) {
        Course course = new Course(courseDTO);
        course.setAuthor(user);
        course.setTopic(topic);
        fillLectures(course);
        saveImage(course, file, new CoursePhoto());
        return courseRepository.save(course);
    }


    @Override
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course rate(Long userId, Long courseId, Integer rating) {
        ratingCheck(rating);
        Course course = courseRepository.getById(courseId);
        setRating(userId, rating, course);
        courseRepository.save(course);
        return course;
    }

    @Override
    @Transactional
    public CoursePhoto saveImage(Course course, MultipartFile imageFile, CoursePhoto coursePhoto) {
        coursePhoto.setCourse(course);
        return coursePhotoService.savePhotoImage(coursePhoto, imageFile);
    }

    private void setRating(Long userId, Integer rating, Course course) {
        courseRatingRepository.save(new CourseRating(rating, userRepository.getById(userId), course));
        course.setAvgRating(course.getCourseRatings().stream()
                .mapToDouble(CourseRating::getRating)
                .average()
                .orElse(0));
        course.setTotalVotes(course.getCourseRatings().size());
    }

    private void ratingCheck(Integer rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException();
        }
    }


    private void fillLectures(Course course) {
        if (course.getLectures() != null && !course.getLectures().isEmpty()) {
            course.getLectures().forEach(lecture ->
                    lecture.setCourse(course)
            );
        } else course.setLectures(new ArrayList<>());
    }
}
