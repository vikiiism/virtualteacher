package com.virtual.teacher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "assignment_solution")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "solution_id")
    private long id;

    private String path;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Min(0)
    @Max(100)
    private int grade;

    public AssignmentSolution(User user, Lecture lecture) {
        this.user = user;
        this.lecture = lecture;
    }

    public AssignmentSolution(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }
}
