package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.AssignmentSolution;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface for lecture assignment solution operations on a service
 *
 */
public interface AssignmentSolutionService {

    /**
     * Prepare saving a given file.
     *
     * @param assignmentSolution must not be {@literal null}.
     * @param taskFile           must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if taskFile is null or empty,
     *                                  if taskFile doesn't match required format,
     *                                  if Files,Paths of getBytes throw IOException
     */
    AssignmentSolution saveAssignmentSolution(AssignmentSolution assignmentSolution, MultipartFile taskFile);

    /**
     * Saves a given AssignmentSolution.
     * Method can be used to change the existing solution.
     *
     * @param assignmentSolution must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    AssignmentSolution save(AssignmentSolution assignmentSolution);

    /**
     * Returns a reference to the solution with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException if file is not found
     */
    AssignmentSolution findById(long id);

    /**
     *
     * @param id must not be {@literal null}.
     * @param grade percents for assignment solution.
     * @throws IllegalArgumentException if grade is invalid.
     * @return graded entity.
     */
    AssignmentSolution gradeAssignmentSolution(long id, String grade);

    /**
     * Returns all instances of Assignment solution filtered.
     *
     * @param userId must be valid.
     * @return all entities filtered by user.
     */
    List<AssignmentSolution> findAllAssignmentSolutionsByUserId(long userId);

    /**
     * Returns all instances of Assignment solution filtered.
     *
     * @param userId must be valid.
     * @param lectureId must be valid.
     * @return all entities filtered by user and lecture.
     */
    List<AssignmentSolution> findAssignmentSolutionByUserAndLecture(long userId, long lectureId);


}
