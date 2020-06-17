package com.virtual.teacher.services;

import com.virtual.teacher.models.User;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContractService {
    private final UserService userService;

    public User currentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userService.findByEmail(username);
        if (!username.equals("anonymousUser") && user.isPresent()) {
            return user.get();
        }
        return null;
    }
}