package java.services;

import com.virtual.teacher.models.*;
import com.virtual.teacher.models.specifications.CourseSpecification;
import com.virtual.teacher.repositories.CourseRepository;
import com.virtual.teacher.services.CourseServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceImplTests {

    @Mock
    CourseRepository repository;

    @InjectMocks
    CourseServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getById_Should_ReturnCourse_When_IdExist() {
        //Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Course(1L, "CourseName1", "descr1"),
                        new Course(2L, "CourseName2", "descr2"),
                        new Course(3L, "CourseName3", "descr3")));
        Mockito.lenient().when(repository.findById(2L))
                .thenReturn(Optional.of(new Course(2L, "CourseName2", "descr2")));

        // Act
        Course result = service.getById(2L);
        long id = result.getId();

        // Assert
        Assert.assertEquals(2, id);
    }


    @Test(expected = IllegalArgumentException.class)
    public void deleteById_Should_ThrowException_When_CourseNotExist() {
        // Arrange
        Course course = new Course();
        course.setId(2L);
        Mockito.lenient().when(repository.findById(2L))
                .thenReturn(Optional.of(course));

        // Act
        service.deleteById(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_Should_ThrowException_When_CourseNotExist() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Course(1L, "CourseName1", "descr1"),
                        new Course(2L, "CourseName2", "descr2"),
                        new Course(3L, "CourseName3", "descr3")));

        // Act
        service.getById(10L);
    }

    @Test
    public void getAll_Should_ReturnList() {
        //Arrange
        when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Course(1L, "LectureName1", "descr1"),
                        new Course(2L, "LectureName2", "descr2"),
                        new Course(3L, "LectureName3", "descr3")));
        // Act
        service.getAll();
    }

    @Test
    public void save_Should_CreateInRepository_When_CourseIsValid() {
        //Arrange
        Course course = new Course(1L, "LectureName1", "descr1");

        //Act
        service.save(course);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(course);
    }


    @Test
    public void getTopRated_Should_ReturnList() {
        //Arrange
        when(repository.findTopSixRatedCourses())
                .thenReturn(Arrays.asList(
                        new Course(1L, "LectureName1", "descr1"),
                        new Course(2L, "LectureName2", "descr2"),
                        new Course(3L, "LectureName3", "descr3")));
        // Act
        service.getTopRated();
    }

    @Test
    public void findCourses_Should_ReturnList() {
        //Arrange
        when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new Course(1L, "LectureName1", "descr1"),
                        new Course(2L, "LectureName2", "descr2"),
                        new Course(3L, "LectureName3", "descr3")));
        // Act
        service.findCourses(Pageable.unpaged());
    }

    @Test
    public void findAllBySearch_Should_ReturnList() {

        //Arrange
        String search = "title";
        when(repository.findAll(CourseSpecification.
                textInAllColumns(search)))
                .thenReturn(Arrays.asList(
                        new Course(1L, "LectureName1", "descr1"),
                        new Course(2L, "LectureName2", "descr2"),
                        new Course(3L, "LectureName3", "descr3")));
        // Act
        service.findAllBySearch(search, Pageable.unpaged());
    }

    @Test(expected = NullPointerException.class)
    public void findAllBySearch_ThrowEception_When_SearchParameterNotExist() {

        //Arrange
        String search = "title";
        when(repository.findAll(CourseSpecification.
                textInAllColumns(search)))
                .thenReturn(Arrays.asList(
                        new Course(1L, "LectureName1", "descr1"),
                        new Course(2L, "LectureName2", "descr2"),
                        new Course(3L, "LectureName3", "descr3")));
        // Act
        service.findAllBySearch(null, Pageable.unpaged());
    }


    @Test
    public void findAllFiltered_Should_ReturnList() {

        //Arrange
        String search = "title";
        when(repository.findAll(CourseSpecification.
                textInAllColumns(search)))
                .thenReturn(Arrays.asList(
                        new Course(1L, "LectureName1", "descr1"),
                        new Course(2L, "LectureName2", "descr2"),
                        new Course(3L, "LectureName3", "descr3")));
        // Act
        service.findAllFiltered("Programming", "Me", Pageable.unpaged());
    }

    @Test(expected = NullPointerException.class)
    public void rate_Should_ThrowException_When_CourseNotExist() {
        //Arrange
        User user = new User();
        user.setId(1L);
        Course course = new Course();
        Mockito.lenient().when(repository.save(course))
                .thenReturn(course);

        // Act
        service.rate(1L, 2L, 3);
    }

    @Test(expected = NullPointerException.class)
    public void rate_Should_ThrowException_When_UserNotExist() {
        //Arrange
        User user = new User();
        Course course = new Course();
        course.setId(2L);
        Mockito.lenient().when(repository.save(course))
                .thenReturn(course);

        // Act
        service.rate(1L, 2L, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rate_Should_ThrowException_When_RatingIsNotInRange() {
        //Arrange
        User user = new User();
        user.setId(1L);
        Course course = new Course();
        course.setId(2L);
        Mockito.lenient().when(repository.save(course))
                .thenReturn(course);


        service.rate(user.getId(), course.getId(), 6);
    }


}
