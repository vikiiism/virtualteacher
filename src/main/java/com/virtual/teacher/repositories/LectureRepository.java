package com.virtual.teacher.repositories;
import com.virtual.teacher.models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Lecture getById(long id);

    @Override
    List<Lecture> findAll();
}
