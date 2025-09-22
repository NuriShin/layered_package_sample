package com.example.layered.project.dto.request;

import com.example.layered.project.domain.Project;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record UpdateProjectRequest  (
        @NotNull Long id,
        @NotBlank String name,
        @PositiveOrZero long pay,
        @NotBlank String supervisorId
) implements BaseProjectRequest {
}
