package com.virtual.teacher.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "title")
    private String title;

    @Size(min = 200,max = 1000)
    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id",referencedColumnName = "task_id")
    private AssignmentTask assignmentTask;

    @OneToMany(mappedBy = "lecture")
    private Collection<AssignmentSolution> assignmentSolutions;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "video_id", referencedColumnName = "video_id")
    private LectureVideo video;

    public Lecture(Long id,String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

}