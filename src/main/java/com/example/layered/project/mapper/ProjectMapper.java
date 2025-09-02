package com.example.layered.project.mapper;

import com.example.layered.project.domain.Project;
import com.example.layered.project.dto.request.CreateProjectRequest;
import com.example.layered.project.dto.request.SearchProjectCondition;
import com.example.layered.project.dto.response.ProjectResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    Long insert(Project project);
    List<ProjectResponse> select(List<SearchProjectCondition> searchParam);
}
