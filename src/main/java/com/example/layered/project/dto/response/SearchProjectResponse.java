package com.example.layered.project.dto.response;

import com.example.layered.project.domain.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record SearchProjectResponse(
        @NotNull Long id,
        @NotBlank String name,
        @PositiveOrZero long pay,
        @NotBlank String supervisorId
) {
    public static SearchProjectResponse fromDomain(Project project) {
        return SearchProjectResponse.builder()
                                    .id(project.getId())
                                    .name(project.getName())
                                    .pay(project.getPay())
                                    .supervisorId(project.getSupervisorId())
                                    .build();
    }

}
