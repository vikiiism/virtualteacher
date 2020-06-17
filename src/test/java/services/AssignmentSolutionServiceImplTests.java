package java.services;

import com.virtual.teacher.models.AssignmentSolution;
import com.virtual.teacher.repositories.AssignmentSolutionRepository;
import com.virtual.teacher.services.AssignmentSolutionServiceImpl;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentSolutionServiceImplTests {
    @Mock
    AssignmentSolutionRepository repository;

    @InjectMocks
    AssignmentSolutionServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById_Should_ReturnAssignmentSolution_When_IdExist() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new AssignmentSolution(1L, "filename.png"),
                        new AssignmentSolution(2L, "filename.jpg"),
                        new AssignmentSolution(3L, "Hashtag.jpg")));

        Mockito.lenient().when(repository.findById(2L))
                .thenReturn(Optional.of(new AssignmentSolution(2L, "Cool.png")));
        // Act
        AssignmentSolution result = service.findById(2L);
        long id = result.getId();

        // Assert
        Assert.assertEquals(2, id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_Should_ThrowException_When_SolutionNotExist() {
        // Arrange
        Mockito.lenient().when(repository.findAll())
                .thenReturn(Arrays.asList(
                        new AssignmentSolution(1L, "filename.png"),
                        new AssignmentSolution(2L, "filename.jpg"),
                        new AssignmentSolution(3L, "Hashtag.jpg")));

        // Act
        service.findById(10L);
    }

    @Test
    public void create_Should_CreateInRepository_When_SolutionIsValid() {
        //Arrange
        AssignmentSolution solution = new AssignmentSolution(1, "light.png");

        //Act
        service.save(solution);

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(solution);
    }


    @Test
    public void gradeAssignmentSolution_Should_UpdateInRepository_When_SolutionIsValid() {
        //Arrange
        AssignmentSolution solution = new AssignmentSolution(1, "light.png");
        Mockito.lenient().when(repository.findById(solution.getId()))
                .thenReturn(Optional.of(solution));

        //Act
        service.gradeAssignmentSolution(solution.getId(), "45");

        //Assert
        Mockito.verify(repository, Mockito.times(1)).save(solution);
    }


    @Test(expected = IllegalArgumentException.class)
    public void gradeAssignmentSolution_Should_ThrowException_When_GradeIsNotInValidRange() {
        AssignmentSolution solution = new AssignmentSolution(1L, "filename.png");
        // Arrange
        when(service.findById(1))
                .thenReturn(solution);
        // Act
        service.gradeAssignmentSolution(solution.getId(), "1500");
    }

    @Test(expected = IllegalArgumentException.class)
    public void gradeAssignmentSolution_Should_ThrowException_When_GradeIsNotInteger() {
        AssignmentSolution solution = new AssignmentSolution(1L, "filename.png");
        // Arrange
        when(service.findById(1))
                .thenReturn(solution);
        // Act
        service.gradeAssignmentSolution(1, "viki");
    }

    @Test
    public void findAllAssignmentSolutionsByUserId_Should_ReturnList() {
        when(repository.findAssignmentSolutionsByUserId(1))
                .thenReturn(Arrays.asList(
                        new AssignmentSolution(1L, "filename.png"),
                        new AssignmentSolution(2L, "filename.jpg"),
                        new AssignmentSolution(3L, "Hashtag.jpg")));
        service.findAllAssignmentSolutionsByUserId(1);
    }

    @Test
    public void findAllAssignmentSolutionsByUserAndLecture_Should_ReturnList() {
        when(repository.findAssignmentSolutionByUserAndLecture(1, 3))
                .thenReturn(Arrays.asList(
                        new AssignmentSolution(1L, "filename.png"),
                        new AssignmentSolution(2L, "filename.jpg"),
                        new AssignmentSolution(3L, "Hashtag.jpg")));
        service.findAssignmentSolutionByUserAndLecture(1, 3);
    }

}
