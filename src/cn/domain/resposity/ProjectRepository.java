package cn.domain.resposity;

import cn.domain.model.Project;

import java.util.List;

public interface ProjectRepository {
    /**
     * 添加或修改项目
     */
    boolean saveProject(Project project);

    /**
     * 根据删除项目信息
     */
    int removeProjectById(Integer id);

    /**
     * 查询所有的项目
     */
    List<Project> getAllProjects();

    /**
     * 根据 id 查询指定的项目
     */
    Project getProjectById(Integer id);

    /**
     * 根据 ClientId 查询指定的项目
     */
    List<Project> getProjectByClientId(Integer clientId);

    /**
     * 根据 name 查询项目
     */
    List<Project> getProjectByName(String name);

    /**
     * 根据 projectType 查询项目
     */
    List<Project> getProjectByType(String projectType);

    /**
     * 根据 state 查询项目
     */
    List<Project> getProjectByState(String state);

    /**
     * 根据 content 查询项目
     */
    List<Project> getProjectByContent(String content);

}
