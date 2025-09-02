package com.example.layered.project.dto.request;

import com.example.layered.project.domain.Project;
import lombok.Getter;

import java.util.Date;

public record CreateProjectRequest(
        String name
        , long pay
        , String supervisorId) {

    public Project toProject() {
        return Project.builder().name(this.name).pay(this.pay).build();
    }
}
