package com.virtual.teacher.repositories;

import com.virtual.teacher.models.AssignmentSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentSolutionRepository extends JpaRepository<AssignmentSolution,Long> {

    @Query(value = "SELECT * FROM assignment_solution WHERE user_id = :userId", nativeQuery = true)
    List<AssignmentSolution> findAssignmentSolutionsByUserId(@Param("userId") long userId);

    @Query(value = "SELECT * FROM assignment_solution WHERE user_id = :userId AND lecture_id = :lectureId", nativeQuery = true)
    List<AssignmentSolution> findAssignmentSolutionByUserAndLecture(@Param("userId") long userId, @Param("lectureId") long lectureId);

}
