package com.virtual.teacher.repositories;

import com.virtual.teacher.models.AssignmentTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentTaskRepository extends JpaRepository<AssignmentTask,Long> {
}
