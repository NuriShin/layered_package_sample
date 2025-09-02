package com.example.layered.project.service;

import com.example.layered.project.domain.Project;
import com.example.layered.project.dto.request.CreateProjectRequest;
import com.example.layered.project.mapper.ProjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectServiceTest {

    @Autowired
    ProjectService projectService;

    @Test
    void create() {

        CreateProjectRequest req = new CreateProjectRequest("테스트", 10000000, "누리");
        assertEquals(2, projectService.create(req));
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
        assertEquals(1, projectService.findByIds(List.of(1)).getFirst().id());

    }

    @Test
    void findByCond() {
    }
}