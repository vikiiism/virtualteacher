package com.virtual.teacher.models.DTOs;

import com.virtual.teacher.constraints.FieldMatch;
import com.virtual.teacher.models.Photo;
import com.virtual.teacher.models.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

//
//@FieldMatch.List({
//        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
//        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
//})
@FieldMatch(first = "password", second = "newPassword", message = "The password fields must match")
@Getter
@Setter
@NoArgsConstructor
public class UserEditDTO {
    private Long id;

    @Size(min = 2, max = 20, message = "Username must be between 3 and 50 symbols")
    private String username;

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 symbols")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 symbols")
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String oldPassword;

    private String password;

    private String newPassword;

    private Boolean hasRequested;

    private Photo photoDto;

    public UserEditDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        oldPassword = "";
        password = "";
        newPassword = "";
        List<Photo> photos = user.getPhotos();
        photoDto = photos.get(photos.size() - 1);
    }
}
