package com.virtual.teacher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "photo_id")
    private Long id;

    private String path;
    private String fileName;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Photo(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }
}
