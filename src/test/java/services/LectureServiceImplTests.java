package java.services;

import com.virtual.teacher.models.Lecture;
import com.virtual.teacher.repositories.LectureRepository;
import com.virtual.teacher.services.LectureServiceImpl;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceImplTests {

    @Mock
    LectureRepository repository;

    @InjectMocks
    LectureServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById_Should_ReturnLecture_When_IdExist() {
        // Arrange
        when(repository.getOne(2L))
                .thenReturn(new Lecture(2L, "LectureName2", "descr2"));
        // Act
        Lecture result = service.getById(2L);
        long id = result.getId();

        // Assert
        Assert.assertEquals(2L, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_Should_ThrowException_When_LectureNotExist() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Lecture(1L, "LectureName1", "descr1"),
                        new Lecture(2L, "LectureName2", "descr2"),
                        new Lecture(3L, "LectureName3", "descr3")));

        // Act
        service.getById(10L);
    }

    @Test
    public void getAll_Should_ReturnList() {
        when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Lecture(1L, "LectureName1", "descr1"),
                        new Lecture(2L, "LectureName2", "descr2"),
                        new Lecture(3L, "LectureName3", "descr3")));
        service.getAll();
    }


}
