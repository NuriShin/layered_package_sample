package com.example.layered.project.mapper;

import com.example.layered.project.domain.Project;
import com.example.layered.project.dto.request.SearchProjectCondition;
import com.example.layered.project.dto.response.SearchProjectResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {
    // Create - 단건 기준, 서비스에서 루프
    Long insert(Project project);
    void update(List<Project> projects);
    SearchProjectResponse select(SearchProjectCondition searchProjectCondition);
    //List<ProjectResponse> select(List<SearchProjectCondition> searchParams);
}
