package java.services;

import com.virtual.teacher.models.User;
import com.virtual.teacher.services.ContractService;
import com.virtual.teacher.services.interfaces.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ContractServiceTests {

    @InjectMocks
    ContractService service;

    @Mock
    UserService userService;

    @Mock
    SecurityContext context;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void currentUser_Should_ReturnNull_When_UserIsAnonymous() {
        Authentication auth = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return new ArrayList<>();
            }

            @Override
            public Object getCredentials() {
                return new Object();
            }

            @Override
            public Object getDetails() {
                return new Object();
            }

            @Override
            public Object getPrincipal() {
                return (Principal) () -> "anonymousUser";
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "anonymousUser";
            }
        };
        //Arrange
        Mockito.lenient().when(userService.findByEmail("anonymousUser"))
                .thenReturn(Optional.of(new User()));
        Mockito.when(context.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(context);
        //Act
        User user = service.currentUser();
        //Assert
        Assert.assertNull(user);
    }
}
