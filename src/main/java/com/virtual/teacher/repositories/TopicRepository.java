package com.virtual.teacher.repositories;

import com.virtual.teacher.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    Topic getTopicByName(String name);

}
