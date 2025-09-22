package com.example.layered.project.dto.request;

import com.example.layered.project.domain.Project;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.springframework.web.bind.annotation.BindParam;

import java.util.Date;

public interface BaseProjectRequest {
    String name();
    long pay();
    String supervisorId();

    default Project toProject() {
        return Project.builder()
                      .name(name())
                      .pay(pay())
                      .supervisorId(supervisorId())
                      .build();
    }
}
