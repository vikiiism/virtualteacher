package com.virtual.teacher.services.interfaces;

import com.virtual.teacher.models.Topic;

import java.util.List;

/**
 * Interface for topic operations on a service
 *
 */
public interface TopicService {
    /**
     * Returns all instances of topic.
     *
     * @return all entities
     */
    List<Topic> getAll();

    /**
     * Saves a given topic.
     * Method can be used to change the existing topic.
     *
     * @param topic must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     * @throws IllegalArgumentException if the instance is not an entity,
     *                                  is a removed entity,
     *                                  or if repository has exception
     */
    Topic create(Topic topic);
}
