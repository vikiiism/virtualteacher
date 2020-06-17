package com.virtual.teacher.repositories;

import com.virtual.teacher.models.LectureVideo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureVideoRepository extends JpaRepository<LectureVideo, Long> {
}
