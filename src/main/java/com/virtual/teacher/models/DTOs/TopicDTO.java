package com.virtual.teacher.models.DTOs;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicDTO {

    @NotNull
    private String name;

}
