package com.virtual.teacher.services;

import com.virtual.teacher.models.*;
import com.virtual.teacher.models.DTOs.UserEditDTO;
import com.virtual.teacher.models.DTOs.UserRegistrationDto;
import com.virtual.teacher.repositories.UserRepository;
import com.virtual.teacher.services.interfaces.CourseService;
import com.virtual.teacher.services.interfaces.LectureVideoService;
import com.virtual.teacher.services.interfaces.PhotoService;
import com.virtual.teacher.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service("UserService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Lazy
    private final CourseService courseService;
    private final LectureVideoService lectureVideoService;
    private final PhotoService photoService;

    @Override
    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null!");
        }
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new IllegalArgumentException(String.format("No user with id %d", id));
        }
        return user.get();
    }

    @Override
    public List<User> findByHasRequested(boolean hasRequested) {
        return userRepository.getByHasRequested(hasRequested);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> saveStudent(UserRegistrationDto userRegistrationDto, MultipartFile file) {
        User user = new User();
        String role = "ROLE_STUDENT";
        return saveUser(userRegistrationDto, file, user, role);
    }

    @Override
    @Transactional
    public void edit(UserEditDTO user, MultipartFile file) {
        User repoUser = findById(user.getId());
        validatePassword(user, repoUser);
        String newPassword = getNewPassword(user, repoUser);
        User newUser = getNewUser(user, repoUser, newPassword);
        userRepository.save(newUser);
        setUserPhoto(file, newUser);
    }

    @Override
    @Transactional
    public Photo saveImage(User user, MultipartFile imageFile, Photo photo) {
        photo.setUser(user);
        return photoService.savePhotoImage(photo, imageFile);
    }

    @Override
    public Photo getLastPhoto(User user) {
        List<Photo> photos = findById(user.getId()).getPhotos();
        if (photos == null || photos.isEmpty()) {
            return saveImage(user, null, new Photo());
        }
        return photos.get(photos.size() - 1);
    }

    @Override
    public Optional<User> saveAdmin(UserRegistrationDto userRegistrationDto, MultipartFile file) {
        User user = new User();
        String role = "ROLE_ADMIN";
        return saveUser(userRegistrationDto, file, user, role);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        User user = userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public User enrollCourse(Long userId, Long courseId) {
        Course course = courseService.getById(courseId);
        User user = userRepository.getById(userId);
        if (user.getEnrolledCourses().contains(course)) {
            throw new IllegalArgumentException("Course is already enrolled");
        }
        user.getEnrolledCourses().add(course);
        course.getUsersEnrolled().add(user);
        courseService.save(course);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllStudents() {
        return userRepository.findAllStudents();
    }

    @Override
    public List<User> findAllTeachers() {
        return userRepository.findAllTeachers();
    }

    @Override
    public List<User> findAllAdmins() {
        return userRepository.findAllAdmins();
    }

    @Override
    @Transactional
    public List<AssignmentSolution> findHomeworksInCourse(long userId, long courseId) {
        return findById(userId).getSolutions()
                .stream()
                .filter(s -> s.getLecture().getCourse().getId() == courseId)
                .collect(Collectors.toList());
    }

    @Override
    public void hasRequestedTrue(long userId) {
        userRepository.hasRequestedTrue(userId);
    }

    @Override
    public void hasRequestedFalse(long userId) {
        userRepository.hasRequestedFalse(userId);
    }

    @Override
    public void softDeleteUserById(long userId) {
        User user = userRepository.getById(userId);
        if (user != null) {
            deleteUser(userId);
        }
    }

    @Override
    public void restoreDeletedAccountById(long userId) {
        userRepository.restoreDeletedAccountById(userId);
    }

    @Override
    public void promoteUserToTeacherById(long userId) {
        userRepository.promoteUserToTeacherById(userId);
    }

    @Override
    public void demoteTeacherToUserById(long userId) {
        userRepository.demoteTeacherToUserById(userId);
    }

    @Override
    public void promoteUserToAdminById(long userId) {
        userRepository.promoteUserToAdmin(userId);
    }


    @Override
    public void isWatched(Long userId, Long videoId) {
        User user = userRepository.getById(userId);
        LectureVideo lectureVideo = lectureVideoService.getById(videoId);
        user.getVideosWatched().add(lectureVideo);
    }


    private Optional<User> saveUser(UserRegistrationDto userRegistrationDto, MultipartFile file,
                                    User user, String role) {
        if (findByEmail(userRegistrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("There is already an account registered with that email");
        }
        user.setUsername(userRegistrationDto.getUsername());
        user.setFirstName(userRegistrationDto.getFirstName());
        user.setLastName(userRegistrationDto.getLastName());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setRoles(new LinkedList<>(Arrays.asList(new Role(role))));
        User newUser = userRepository.save(user);
        saveImage(newUser, file, new Photo());
        newUser = userRepository.save(newUser);
        return Optional.of(newUser);
    }

    private void deleteUser(long userId) {
        Collection<Course> courseList = userRepository.getById(userId).getCreatedCourses();
        courseList.forEach(course -> courseService.deleteById(course.getId()));
        userRepository.softDeleteUserById(userId);
    }

    private void setUserPhoto(MultipartFile file, User newUser) {
        Photo newPhoto = new Photo();
        newPhoto.setUser(newUser);
        newPhoto = saveImage(newUser, file, newPhoto);
        if (newPhoto != null) {
            newUser.getPhotos().add(newPhoto);
            userRepository.save(newUser);
        }
    }

    private User getNewUser(UserEditDTO user, User repoUser, String newPassword) {
        return new User(repoUser.getId(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail(), newPassword
                , repoUser.isHasRequested(), true, repoUser.getPhotos(), repoUser.getSolutions(),
                repoUser.getRoles(), repoUser.getEnrolledCourses(), repoUser.getCompletedCourses(),
                repoUser.getCreatedCourses(), repoUser.getVideosWatched()
        );
    }

    private String getNewPassword(UserEditDTO user, User repoUser) {
        String newPassword;
        if (user.getPassword().isEmpty()) {
            newPassword = repoUser.getPassword();
        } else {
            newPassword = passwordEncoder.encode(user.getPassword());
        }
        return newPassword;
    }

    private void validatePassword(UserEditDTO user, User repoUser) {
        if (!passwordEncoder.matches(user.getOldPassword(), repoUser.getPassword())) {
            throw new IllegalArgumentException("Try to guess your old password! ");
        }
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
