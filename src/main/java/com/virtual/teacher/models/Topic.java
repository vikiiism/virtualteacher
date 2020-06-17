package com.virtual.teacher.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "topic")
    private List<Course> courses;

    public Topic(String name) {
        this.name = name;
    }
}