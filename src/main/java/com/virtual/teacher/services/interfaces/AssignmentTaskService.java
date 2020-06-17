package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.AssignmentTask;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for lecture assignment solution operations on a service
 *
 */

public interface AssignmentTaskService {

    /**
     * Prepare saving a given file.
     *
     * @param assignmentTask must not be {@literal null}.
     * @param taskFile           must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if taskFile is null or empty,
     *                                  if taskFile doesn't match required format,
     *                                  if Files,Paths of getBytes throws IOException
     */
    AssignmentTask saveAssignmentTask(AssignmentTask assignmentTask, MultipartFile taskFile);

    /**
     * Saves a given AssignmentTask.
     * Method can be used to change the existing solution.
     *
     * @param assignmentTask must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    AssignmentTask save(AssignmentTask assignmentTask);

    /**
     * Returns a reference to the task with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException if file is not found
     */
    AssignmentTask findById(long id);

}
