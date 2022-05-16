package cn.application;

import cn.domain.model.Project;
import cn.domain.resposity.ClientRepository;
import cn.domain.resposity.ProjectRepository;
import cn.infrastructure.repositoryImpl.ClientRepositoryImpl;
import cn.infrastructure.repositoryImpl.ProjectRepositoryImpl;

import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepositoryImpl();
    private ClientRepository clientRepository = new ClientRepositoryImpl();
    public List<Project> queryProjects() {
        return projectRepository.getAllProjects();
    }

    public Project queryProjectById(Integer id) {
        return projectRepository.getProjectById(id);
    }
    public List<Project> queryProjectByClientId(Integer id) {
        return projectRepository.getProjectByClientId(id);
    }
    public List<Project> queryProjectByState(String state) {
        return projectRepository.getProjectByState(state);
    }
    public List<Project> queryProjectByType(String type) {
        return projectRepository.getProjectByType(type);
    }
    public List<Project> queryProjectByContent(String content) {
        return projectRepository.getProjectByContent(content);
    }
    public List<Project> queryProjectByName(String name) {
        return projectRepository.getProjectByName(name);
    }
    public boolean saveProject(Project project) {
        if(clientRepository.getClientById(project.getClientId()) == null){
            return false;
        }
        return projectRepository.saveProject(project);
    }
    public void deleteProjectById(Integer id) {
        projectRepository.removeProjectById(id);
    }
}
