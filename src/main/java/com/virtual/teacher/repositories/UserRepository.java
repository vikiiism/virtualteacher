package com.virtual.teacher.repositories;

import com.virtual.teacher.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    User getById(long id);

    @Query(value = "select * from users where hasRequested = true",nativeQuery = true)
    List<User> getByHasRequested(boolean hasRequested);

    @Query(value = "SELECT * " +
            "FROM users, user_role, roles " +
            "WHERE users.user_id = user_role.user_id " +
            "AND user_role.role_id = roles.role_id " +
            "AND roles.role_name = 'ROLE_STUDENT'", nativeQuery = true)
    List<User> findAllStudents();

    @Query(value = "SELECT * " +
            "FROM users, user_role, roles " +
            "WHERE users.user_id = user_role.user_id " +
            "AND user_role.role_id = roles.role_id " +
            "AND roles.role_name = 'ROLE_TEACHER'", nativeQuery = true)
    List<User> findAllTeachers();

    @Query(value = "SELECT * " +
            "FROM users, user_role, roles " +
            "WHERE users.user_id = user_role.user_id " +
            "AND user_role.role_id = roles.role_id " +
            "AND roles.role_name = 'ROLE_ADMIN'", nativeQuery = true)
    List<User> findAllAdmins();

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET hasRequested = 1 WHERE u.user_id = :userId ", nativeQuery = true)
    void hasRequestedTrue(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET hasRequested = 0 WHERE u.user_id = :userId ", nativeQuery = true)
    void hasRequestedFalse(@Param("userId") long userId);


    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET enabled = 0 WHERE u.user_id = :userId ", nativeQuery = true)
    void softDeleteUserById(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET enabled = 1 WHERE u.user_id = :userId", nativeQuery = true)
    void restoreDeletedAccountById(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE roles r, user_role ur SET r.role_name = 'ROLE_TEACHER' " +
            "WHERE r.role_id = ur.role_id " +
            "AND ur.user_id = :userId", nativeQuery = true)
    void promoteUserToTeacherById(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE roles r, user_role ur SET r.role_name = 'ROLE_STUDENT' " +
            "WHERE r.role_id = ur.role_id " +
            "AND ur.user_id = :userId", nativeQuery = true)
    void demoteTeacherToUserById(@Param("userId") long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE roles r, user_role ur SET r.role_name = 'ROLE_ADMIN' " +
            "WHERE r.role_id = ur.role_id " +
            "AND ur.user_id = :userId", nativeQuery = true)
    void promoteUserToAdmin(@Param("userId") long userId);

}