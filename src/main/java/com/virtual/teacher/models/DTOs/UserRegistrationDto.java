package com.virtual.teacher.models.DTOs;

import com.virtual.teacher.constraints.FieldMatch;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})

@Getter
@Setter
public class UserRegistrationDto {

    @Size(min = 2, max = 20, message = "Username must be between 3 and 50 symbols")
    private String username;

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 symbols")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 symbols")
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @Size(min = 6, max = 20, message = "password must be between 6 and 20 symbols")
    private String password;

    @NotEmpty
    private String confirmPassword;

    @Override
    public String toString() {
        return "User{username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + "*********" + '\'' +
                '}';
    }

}
