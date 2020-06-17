package java.services;

import com.virtual.teacher.models.Photo;
import com.virtual.teacher.repositories.PhotoRepository;
import com.virtual.teacher.services.PhotoServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceImplTests {

    @Mock
    PhotoRepository repository;

    @InjectMocks
    PhotoServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_Should_CreateInRepository_When_PhotoIsValid() {
        //Arrange
        Photo photo = new Photo(1, "light.png");

        //Act
        service.save(photo);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(photo);
    }
}
