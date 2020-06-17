package java.services;

import com.virtual.teacher.models.User;
import com.virtual.teacher.repositories.UserRepository;
import com.virtual.teacher.services.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {
    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl service;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllStudents_Should_ReturnList() {
        when(repository.findAllStudents())
                .thenReturn(Arrays.asList(new User(), new User(), new User()));
        service.findAllStudents();
    }

    @Test
    public void findAllTeachers_Should_ReturnList() {
        when(repository.findAllStudents())
                .thenReturn(Arrays.asList(new User(), new User(), new User()));
        service.findAllTeachers();
    }

    @Test
    public void findAllAdmins_Should_ReturnList() {
        when(repository.findAllStudents())
                .thenReturn(Arrays.asList(new User(), new User(), new User()));
        service.findAllAdmins();
    }

    @Test
    public void findById_Should_ReturnUser() {
        //Arrange
        when(repository.findById(2L))
                .thenReturn(Optional.of(new User()));
        // Act
        service.findById(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_Should_ThrowsException_When_UserNotExists() {
        //Arrange
        User user = new User();
        user.setId(2);
        Mockito.lenient().when(repository.findById(2L))
                .thenReturn(Optional.of(user));
        // Act
        service.findById(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_Should_ThrowsException_When_IdIsNull() {
        // Act
        service.findById(null);
    }

    @Test
    public void findByHasRequested_Should_ReturnList() {
        when(repository.getByHasRequested(true))
                .thenReturn(Arrays.asList(new User(), new User()));
        service.findByHasRequested(true);
    }

    @Test
    public void findByEmail_Should_ReturnUser_WhenExist() {
        when(repository.findByEmail("viki@mail.bg"))
                .thenReturn(Optional.of(new User()));
        service.findByEmail("viki@mail.bg");
    }

    @Test
    public void update_Should_CallRepository() {
        service.update(new User());
    }

    @Test
    public void hasRequestedTrue_Should_CallRepository() {
        service.hasRequestedTrue(3);
    }

    @Test
    public void hasRequestedFalse_Should_CallRepository() {
        service.hasRequestedFalse(3);
    }

    @Test
    public void promoteUserToTeacherById_Should_CallRepository() {
        service.promoteUserToTeacherById(3);
    }

    @Test
    public void demoteTeacherToUserById_Should_CallRepository() {
        service.demoteTeacherToUserById(3);
    }

    @Test
    public void promoteUserToAdminById_Should_CallRepository() {
        service.promoteUserToAdminById(3);
    }

    @Test
    public void restoreDeletedAccountById_Should_CallRepository() {
        service.restoreDeletedAccountById(3);
    }
}
