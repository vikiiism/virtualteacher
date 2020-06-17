package com.virtual.teacher.repositories;

import com.virtual.teacher.models.CourseRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRatingRepository extends JpaRepository<CourseRating, Long> {
}
