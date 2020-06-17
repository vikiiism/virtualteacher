package java.services;

import com.virtual.teacher.models.CoursePhoto;
import com.virtual.teacher.repositories.CoursePhotoRepository;
import com.virtual.teacher.services.CoursePhotoImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoursePhotoServiceImplTests {

    @Mock
    CoursePhotoRepository repository;

    @InjectMocks
    CoursePhotoImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_Should_CreateInRepository_When_CoursePhotoIsValid() {
        //Arrange
        CoursePhoto coursePhoto = new CoursePhoto(1, "light.png");

        //Act
        service.save(coursePhoto);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(coursePhoto);
    }

}
