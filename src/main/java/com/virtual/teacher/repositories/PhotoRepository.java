package com.virtual.teacher.repositories;

import com.virtual.teacher.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo,Long> {
}
