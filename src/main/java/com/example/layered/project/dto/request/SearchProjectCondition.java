package com.example.layered.project.dto.request;

import com.example.layered.project.domain.Project;

public record SearchProjectCondition(
        int id
        , String name
        , long pay
        , String supervisorId) {



}
