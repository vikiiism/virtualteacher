package com.virtual.teacher.models.DTOs;

import com.virtual.teacher.models.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    @Size(min = 2, max = 200, message = "Course title must be between 2 and 200 symbols")
    private String title;
    @Size(min = 200, max = 1000, message = "Course description must be between 200 and 1000 symbols")
    private String description;
    private String teacher;
    private Topic topic;
    @Max(value = 100, message = "Course grade must be in percents")
    private double grade;
}
