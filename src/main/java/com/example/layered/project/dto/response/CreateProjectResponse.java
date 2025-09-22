package com.example.layered.project.dto.response;

import com.example.layered.project.domain.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

@Builder
public record CreateProjectResponse(
        @NotNull Long id,
        @NotBlank String name,
        @PositiveOrZero long pay,
        @NotBlank String supervisorId
) {
    public static CreateProjectResponse fromDomain(Project project) {
        return CreateProjectResponse.builder()
                              .id(project.getId())
                              .name(project.getName())
                              .pay(project.getPay())
                              .supervisorId(project.getSupervisorId())
                              .build();
    }

}
