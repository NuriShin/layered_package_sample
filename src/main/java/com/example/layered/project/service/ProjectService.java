package com.example.layered.project.service;

import com.example.layered.project.domain.Project;
import com.example.layered.project.dto.request.*;
import com.example.layered.project.dto.response.CreateProjectResponse;
import com.example.layered.project.dto.response.SearchProjectResponse;
import com.example.layered.project.mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;

    // 단건 insert
    @Transactional
    public CreateProjectResponse create(CreateProjectRequest request) {

        Project project = request.toProject();
        projectMapper.insert(project);

        return CreateProjectResponse.fromDomain(project);
    }

    // 1 - nn 단위 insert
    @Transactional
    public List<CreateProjectResponse> createAll(List<CreateProjectRequest> request) {

        return request.stream()
                      .map(this::create).toList();

    }

    // nnn 단위 insert -> mapper 내부에서 foreach (update 참고)
    // nnnn 단위 insert -> batch 사용

    // 1 ~ nnn 단위 update 예시
    @Transactional
    public void update(UpdateProjectListRequest request) {
        projectMapper.update(request.toProjects());
    }

    // 조회
    public SearchProjectResponse findByCond(SearchProjectCondition searchProjectCondition) {
        return projectMapper.select(searchProjectCondition);
    }

    
   
}
