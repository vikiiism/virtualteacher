package com.virtual.teacher.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.virtual.teacher.models.DTOs.CourseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "title")
    @NotNull
    private String title;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Size(min = 200,max = 1000)
    @Column(name = "description")
    private String description;

    @Column(name = "grade")
    @ColumnDefault("0")
    private double grade;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lecture> lectures = new ArrayList<>();

    @NotNull
    @Column(name = "total_votes")
    private int totalVotes = 0;

    @NotNull
    @Column(name = "avg_rating")
    private Double avgRating = 0.0;

    @Column(name = "enabled")
    @ColumnDefault("false")
    private boolean enabled = false;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<CourseRating> courseRatings;

    @ManyToMany(mappedBy = "enrolledCourses")
    private Collection<User> usersEnrolled;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CoursePhoto> coursePhotos;

    public Course(CourseDTO courseDTO) {
        title = courseDTO.getTitle();
        description = courseDTO.getDescription();
        grade = courseDTO.getGrade();
        enabled = false;
    }

    public Course(@NotNull String title, Topic topic, User author, String description, double grade, List<Lecture> lectures, @NotNull int totalVotes, @NotNull Double avgRating, Set<CourseRating> courseRatings, Collection<User> usersEnrolled, List<CoursePhoto> coursePhotos) {
        this.title = title;
        this.topic = topic;
        this.author = author;
        this.description = description;
        this.grade = grade;
        this.lectures = lectures;
        this.totalVotes = totalVotes;
        this.avgRating = avgRating;
        this.courseRatings = courseRatings;
        this.usersEnrolled = usersEnrolled;
        this.coursePhotos = coursePhotos;
        enabled = false;
    }

    public Course(Long id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
