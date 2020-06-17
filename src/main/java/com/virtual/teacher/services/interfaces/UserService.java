package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.AssignmentSolution;
import com.virtual.teacher.models.DTOs.UserEditDTO;
import com.virtual.teacher.models.DTOs.UserRegistrationDto;
import com.virtual.teacher.models.Photo;
import com.virtual.teacher.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

/**
 * Interface for user operations on a service
 *
 */
public interface UserService extends UserDetailsService {
    /**
     * Returns all instances of users with role Student.
     *
     * @return all student entities
     */
    List<User> findAllStudents();

    /**
     * Returns all instances of users with role Teacher.
     *
     * @return all teacher entities
     */
    List<User> findAllTeachers();

    /**
     * Returns all instances of users with role Admin.
     *
     * @return all admin entities
     */
    List<User> findAllAdmins();

    /**
     * Returns last uploaded homeworks
     *
     * @param userId identity
     * @param courseId identity for requested course files
     * @return last uploaded files to lectures
     */
    List<AssignmentSolution> findHomeworksInCourse(long userId, long courseId);

    /**
     * Returns a reference to the user with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException if id is null,
     *                                  if entity is not found
     */
    User findById(Long id);

    /**
     * Returns a list of users that has request value like given parameter.
     *
     * @param hasRequested is filter by value.
     * @return a list of entities with the given column.
     */
    List<User> findByHasRequested(boolean hasRequested);

    /**
     * Returns a reference to the user with the given email.
     *
     * @param email must not be {@literal null}.
     * @return entity if exists in repository
     */
    Optional<User> findByEmail(String email);

    /**
     * Get last photo from user's photo list.
     *
     * @param user must not be {@literal null}.
     * @return reference to user's last photo
     */
    Photo getLastPhoto(User user);

    /**
     * Adds the given course to the enrolled courses user's list
     *
     * @param userId must not be {@literal null}.
     * @return update user entity
     */

    User enrollCourse(Long userId, Long courseId);


    User update(User user);

    /**
     * Saving user with profile picture
     *
     * @param userRegistrationDto must not be {@literal null}.
     * @param file                is profile picture.
     * @return optional saved entity.
     * @throws IllegalArgumentException if file is null or empty,
     *                                  if file doesn't match required format,
     *                                  if Files,Paths of getBytes throw IOException
     */
    Optional<User> saveStudent(UserRegistrationDto userRegistrationDto, MultipartFile file);

    /**
     * Saving admin with profile picture
     *
     * @param userRegistrationDto must not be {@literal null}.
     * @param file                is profile picture.
     * @return optional saved entity.
     * @throws IllegalArgumentException if file is null or empty,
     *                                  if file doesn't match required format,
     *                                  if Files,Paths of getBytes throw IOException
     */
    Optional<User> saveAdmin(UserRegistrationDto userRegistrationDto, MultipartFile file);

    /**
     * Saves a given AssignmentSolution.
     * Method can be used to change the existing solution.
     *
     * @param user      must not be {@literal null}.
     * @param imageFile is photo file
     * @param photo     is reference to to be uploaded object
     * @return the saved entity will never be {@literal null}.
     */
    Photo saveImage(User user, MultipartFile imageFile, Photo photo) throws Exception;

    /**
     * Change requested field in user profile
     *
     * @param user must not be {@literal null} and must exist in repository.
     * @param file is new profile picture.
     * @throws IllegalArgumentException if file is null or empty,
     *                                  if file doesn't match required format,
     *                                  if Files,Paths of getBytes throw IOException
     */
    void edit(UserEditDTO user, MultipartFile file);

    /**
     * Notify lecture that video is watched from user.
     *
     * @param userId  must not be {@literal null}.
     * @param videoId must not be {@literal null}.
     */
    void isWatched(Long userId, Long videoId);

    /**
     * Accept promotion to teacher
     *
     * @param userId identifier of pending student
     */
    void hasRequestedTrue(long userId);

    /**
     * Decline promotion to teacher
     *
     * @param userId identifier of pending student
     */
    void hasRequestedFalse(long userId);

    /**
     * Promote referenced student to teacher
     *
     * @param userId must not be {@literal null}.
     */
    void promoteUserToTeacherById(long userId);

    /**
     * Demote referenced teacher to student
     *
     * @param userId must not be {@literal null}.
     */
    void demoteTeacherToUserById(long userId);

    /**
     * Promote referenced teacher to admin
     *
     * @param userId must not be {@literal null}.
     */
    void promoteUserToAdminById(long userId);

    /**
     * Disable user account
     *
     * @param userId must not be {@literal null}.
     */
    void softDeleteUserById(long userId);

    /**
     * Enable existing user account
     *
     * @param userId must not be {@literal null}.
     */
    void restoreDeletedAccountById(long userId);


}
