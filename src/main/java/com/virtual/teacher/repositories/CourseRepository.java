package com.virtual.teacher.repositories;


import com.virtual.teacher.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    @Override
    Page<Course> findAll(Specification<Course> specification, Pageable pageable);

    Course getById(long id);

    @Query(value = "SELECT * FROM " +
            "(SELECT  * FROM courses c WHERE c.enabled = true ORDER BY total_votes DESC LIMIT 10)" +
            " as generated_table ORDER BY avg_rating DESC, total_votes DESC LIMIT 6",
            nativeQuery = true)
    List<Course> findTopSixRatedCourses();

    @Modifying
    @Query(value = "DELETE courses, lectures " +
            "FROM courses " +
            "         INNER JOIN lectures " +
            "WHERE courses.course_id = :courseId " +
            "  AND courses.course_id = lectures.course_id", nativeQuery = true)
    void deleteCourseAndContainedLecturesById(@Param("courseId") Long courseId);

}
