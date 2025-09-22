package com.example.layered.project.dto.request;

import com.example.layered.project.domain.Project;
import jakarta.validation.Valid;
import lombok.Builder;

import java.util.List;

// 필수 아님
@Builder
public record UpdateProjectListRequest(@Valid List<UpdateProjectRequest> projects) {

    public List<Project> toProjects() {
        return this.projects.stream()
                            .map(BaseProjectRequest::toProject)
                            .toList();
    }
}
