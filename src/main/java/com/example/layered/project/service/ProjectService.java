package com.example.layered.project.service;

import com.example.layered.project.dto.request.CreateProjectRequest;
import com.example.layered.project.dto.request.SearchProjectCondition;
import com.example.layered.project.dto.response.ProjectResponse;
import com.example.layered.project.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;

    public Long create(CreateProjectRequest request) {
        return projectMapper.insert(request.toProject());
    }
    
    public void update() {
        // TODO: Implement approval status update logic
    }

    public List<ProjectResponse> findByIds(List<Integer> ids) {
        return projectMapper.select(ids.stream()
                            .map(id -> new SearchProjectCondition(id, null, -1L, null))
                            .toList());
    }

    public void findByCond() {
        // TODO: Implement approval status update logic
    }
}