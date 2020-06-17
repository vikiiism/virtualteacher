package java.services;

import com.virtual.teacher.models.Topic;
import com.virtual.teacher.repositories.TopicRepository;
import com.virtual.teacher.services.TopicServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TopicServiceImplTests {
    @Mock
    TopicRepository repository;

    @InjectMocks
    TopicServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAll_Should_ReturnTags() {
        //Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Topic("Programming"),
                        new Topic("Maths")
                ));
        //Act
        List<Topic> result = service.getAll();
    }

    @Test
    public void create_Should_CreateInRepository_When_TopicIsValid() {
        //Arrange
        Topic topic = new Topic("Fav");

        //Act
        service.create(topic);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(topic);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_Should_ThrowException_When_NameIsDuplicate() {
        //Arrange
        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Hashtag"));
        when(repository.getTopicByName("Hashtag"))
                .thenReturn(new Topic("Hashtag"));
        Mockito.lenient().when(repository.findAll())
                .thenReturn(topics);

        //Act
        service.create(new Topic("Hashtag"));
    }

    @Test
    public void create_Should_UpdateInRepository_When_TopicIsValid() {
        //Arrange
        Topic topic = new Topic("Fresh");
        repository.save(topic);
        topic = new Topic("freshhy");


        //Act
        service.create(topic);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(topic);
    }
}
