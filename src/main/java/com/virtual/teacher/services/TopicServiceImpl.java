package com.virtual.teacher.services;

import com.virtual.teacher.models.Topic;
import com.virtual.teacher.repositories.TopicRepository;
import com.virtual.teacher.services.interfaces.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TransactionRequiredException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic create(Topic topic) {
        checkExists(topic);
        String exceptionMessage;
        try {
            return topicRepository.save(topic);
        } catch (IllegalArgumentException iae) {
            exceptionMessage = "Not topic instance or removed one!";
        } catch (TransactionRequiredException tre) {
            exceptionMessage = "Repository error!";
        }
        throw new IllegalArgumentException(exceptionMessage);
    }

    private void checkExists(Topic topic) {
        if (topicRepository.getTopicByName(topic.getName()) != null) {
            throw new IllegalArgumentException("Topic already exists!");
        }
    }
}
