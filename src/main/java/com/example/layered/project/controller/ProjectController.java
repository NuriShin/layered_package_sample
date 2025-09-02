package com.example.layered.project.controller;

import com.example.layered.common.response.ApiResponse;
import com.example.layered.project.domain.Project;
import com.example.layered.project.dto.request.CreateProjectRequest;
import com.example.layered.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class ProjectController {

    private ProjectService projectService;

    @PostMapping
    public ApiResponse createProject(@RequestBody CreateProjectRequest req) {
        return ApiResponse.success(projectService.create(req));
    }

    @GetMapping("/{projectId}")
    public ApiResponse getProject(@PathVariable int projectId) {
         return ApiResponse.success(projectService.findByIds(List.of(projectId)));
    }
}
