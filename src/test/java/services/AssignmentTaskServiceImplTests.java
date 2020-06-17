package java.services;

import com.virtual.teacher.models.AssignmentTask;
import com.virtual.teacher.repositories.AssignmentTaskRepository;
import com.virtual.teacher.services.AssignmentTaskServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentTaskServiceImplTests {

    @Mock
    AssignmentTaskRepository repository;

    @InjectMocks
    AssignmentTaskServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById_Should_ReturnAssignmentTask_When_IdExist() {
        //Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new AssignmentTask(1L, "filename.png"),
                        new AssignmentTask(2L, "filename.jpg"),
                        new AssignmentTask(3L, "Hashtag.jpg")));
        Mockito.lenient().when(repository.findById(2L))
                .thenReturn(Optional.of(new AssignmentTask(2L, "Cool.png")));
        // Act
        AssignmentTask result = service.findById(2L);
        long id = result.getId();

        // Assert
        Assert.assertEquals(2, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_Should_ThrowException_When_TaskNotExist() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new AssignmentTask(1L, "filename.png"),
                        new AssignmentTask(2L, "filename.jpg"),
                        new AssignmentTask(3L, "Hashtag.jpg")));

        // Act
        service.findById(10L);
    }

    @Test
    public void create_Should_CreateInRepository_When_TaskIsValid() {
        //Arrange
        AssignmentTask task = new AssignmentTask(1, "light.png");

        //Act
        service.save(task);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(task);
    }

}
