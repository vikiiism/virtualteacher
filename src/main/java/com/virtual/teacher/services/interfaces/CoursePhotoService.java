package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.CoursePhoto;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for lecture assignment solution operations on a service
 *
 */

public interface CoursePhotoService {

    /**
     * Prepare saving a given file.
     *
     * @param coursePhoto must not be {@literal null}.
     * @param imageFile must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if imageFile doesn't match required format,
     *                                  if Files,Paths of getBytes throws IOException if Files,Paths of getBytes doesn't match required format
     */
    CoursePhoto savePhotoImage(CoursePhoto coursePhoto, MultipartFile imageFile);

    /**
     * Saves a given Course Photo.
     * Method can be used to change the existing photo.
     *
     * @param coursePhoto must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    CoursePhoto save(CoursePhoto coursePhoto);

}
