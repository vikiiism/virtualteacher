package com.virtual.teacher.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lecture_video")
@Where(clause = "watched=0")
public class LectureVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "video_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "code")
    private String code;

    @Column(name = "watched")
    @ColumnDefault("false")
    private boolean watched;

    @OneToOne(mappedBy = "video")
    private Lecture lecture;

    @ManyToMany(mappedBy = "videosWatched")
    private Collection<User> usersWatched;

    public LectureVideo(Long id,String code){
        this.id=id;
        this.code = code;
    }
    public LectureVideo(String code){
        this.code = code;
    }

}
