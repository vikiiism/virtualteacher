package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.LectureVideo;

/**
 * Interface for video in lecture operations on a service
 *
 */
public interface LectureVideoService {
    /**
     * Returns a reference to the video with the given identifier.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @throws IllegalArgumentException when id is null, when entity is not found
     */
    LectureVideo getById(Long id);

    /**
     * Saves a given topic.
     * Method can be used to change the existing topic.
     *
     * @param lectureVideo must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if lectureVideo is null,
     *                                  if the instance is not an entity,
     *                                  is a removed entity,
     *                                  or if repository has exception
     */
    LectureVideo save(LectureVideo lectureVideo);

}
