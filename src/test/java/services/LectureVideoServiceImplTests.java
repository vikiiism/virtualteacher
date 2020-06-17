package java.services;

import com.virtual.teacher.models.LectureVideo;
import com.virtual.teacher.repositories.LectureVideoRepository;
import com.virtual.teacher.services.LectureVideoServiceImpl;
import com.virtual.teacher.services.functions.interfaces.YoutubeCodeExtractor;
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

@RunWith(MockitoJUnitRunner.class)
public class LectureVideoServiceImplTests {
    @Mock
    LectureVideoRepository repository;
    @Mock
    YoutubeCodeExtractor codeExtractor;

    @InjectMocks
    LectureVideoServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById_Should_ReturnVideo_When_IdExist() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new LectureVideo(1L, "Fresh"),
                        new LectureVideo(2L, "Cool"),
                        new LectureVideo(3L, "Hashtag")));

        Mockito.lenient().when(repository.getOne(2L))
                .thenReturn(new LectureVideo(2L, "Cool"));
        // Act
        LectureVideo result = service.getById(2L);
        long id = result.getId();

        // Assert
        Assert.assertEquals(2, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_Should_ThrowException_When_IdIsNull() {
        service.getById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById_Should_ThrowException_When_VideoNotExit() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new LectureVideo("Fresh"),
                        new LectureVideo("Cool"),
                        new LectureVideo("Hashtag")));

        // Act
        LectureVideo result = service.getById(10L);
    }

    @Test
    public void create_Should_CreateInRepository_When_VideoIsValid() {
        //Arrange
        LectureVideo video = new LectureVideo("https://www.youtube.com/watch?v=cLSHjCiB7Xs");
        Mockito.when(codeExtractor.extractCode("https://www.youtube.com/watch?v=cLSHjCiB7Xs"))
                .thenReturn("cLSHjCiB7Xs");
        //Act
        service.save(video);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(video);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_Should_ThrowException_When_ArgIsNull() {
        service.save(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void create_Should_ThrowException_When_CodeIsEmpty() {
        service.save(new LectureVideo(""));
    }
}
