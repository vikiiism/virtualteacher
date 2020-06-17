package com.virtual.teacher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "course_photos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoursePhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "course_photos_id")
    private Long id;

    private String path;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public CoursePhoto(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

}
