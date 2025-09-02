package com.example.layered.project.dto.response;

import com.example.layered.project.domain.Project;

import java.util.Date;

public record ProjectResponse(
        int id
        , String name
        , long pay
        , String supervisorId
        , Date createdAt
) {}
