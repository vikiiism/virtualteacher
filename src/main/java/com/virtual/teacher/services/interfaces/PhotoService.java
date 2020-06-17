package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.Photo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface for user operations on a service
 *
 */

public interface PhotoService {

    /**
     * Prepare saving a given file.
     *
     * @param photo must not be {@literal null}.
     * @param imageFile must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if imageFile doesn't match required format,
     *                                  if Files,Paths of getBytes throws IOException if Files,Paths of getBytes doesn't match required format
     */

    Photo savePhotoImage(Photo photo, MultipartFile imageFile);

    /**
     * Saves a given Photo.
     * Method can be used to change the existing photo.
     *
     * @param photo must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    Photo save(Photo photo);
}
