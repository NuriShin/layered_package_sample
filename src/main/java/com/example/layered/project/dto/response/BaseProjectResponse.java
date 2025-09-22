package com.example.layered.project.dto.response;

import com.example.layered.project.domain.Project;

import java.util.Date;

public interface BaseProjectResponse {
    Long id();
    String name();
    Long pay();
    String supervisorId();
    Date createdAt();

}
