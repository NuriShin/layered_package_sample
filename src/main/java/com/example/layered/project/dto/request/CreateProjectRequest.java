package com.example.layered.project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;

@Builder
public record CreateProjectRequest(
        @NotBlank String name,
        @PositiveOrZero long pay,
        @NotBlank String supervisorId
) implements BaseProjectRequest {
}
