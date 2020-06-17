package com.virtual.teacher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "assignment_task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentTask {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "task_id")
    private long id;

    @OneToOne(mappedBy = "assignmentTask")
    private Lecture lecture;

    private String path;

    private String fileName;

    public AssignmentTask(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

}
