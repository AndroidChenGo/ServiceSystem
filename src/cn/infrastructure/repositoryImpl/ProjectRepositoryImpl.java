package cn.infrastructure.repositoryImpl;

import cn.domain.model.Project;
import cn.domain.resposity.ProjectRepository;
import cn.infrastructure.util.StringUtil;

import java.util.List;

public class ProjectRepositoryImpl extends BaseRespository implements ProjectRepository {
    @Override
    public boolean saveProject(Project project) {
        if(0 == project.getId()){
            String id = StringUtil.getRandomNumberString(6);;
            while(getProjectById(Integer.parseInt(id.trim())) != null){
                id = StringUtil.getRandomNumberString(10);
            }
            String sql ="insert into project(`id`,`name`,`type`,`state`,`clientId`,`content`) values(?,?,?,?,?,?)";
            if(-1 != update(sql, Integer.parseInt(id),project.getName(),project.getType(),
                    project.getState(),project.getClientId(),project.getContent()))
                return true;
            else return false;
        }else{
            String sql = "update project set `name`=?,`type`=?,`state`=?,`content`=?,`content`=? where id = ?";
            if(-1 != update(sql,project.getName(),project.getType(),project.getState(),project.getClientId(),project.getContent(),project.getId())){
                return true;
            }else return false;
        }
    }

    @Override
    public int removeProjectById(Integer id) {
        String sql = "delete from project where id = ?";
        return update(sql, id);
    }

    @Override
    public List<Project> getAllProjects() {
        String sql = "select `id`,`name`,`type`,`state`,`clientId`,`content` from project";
        return queryForList(Project.class, sql);
    }

    @Override
    public Project getProjectById(Integer id) {
        String sql = "select `id` , `name` , `type` , `state`,`clientId`,`content` from project where id = ?";
        return queryForOne(Project.class, sql,id);
    }

    @Override
    public List<Project> getProjectByClientId(Integer clientId) {
        String sql = "select `id` , `name` , `type` , `state`,`clientId`,`content` from project where clientId = ?";
        return queryForList(Project.class, sql,clientId);
    }

    @Override
    public List<Project> getProjectByName(String name) {
        String sql = "select `id` , `name` , `type` , `state`,`clientId`,`content` from project where name = ?";
        return queryForList(Project.class, sql,name);
    }

    @Override
    public List<Project> getProjectByType(String projectType) {
        String sql = "select `id` , `name` , `type` , `state`,`clientId`,`content` from project where type = ?";
        return queryForList(Project.class, sql,projectType);
    }

    @Override
    public List<Project> getProjectByState(String state) {
        String sql = "select `id` , `name` , `type` , `state`,`clientId`,`content` from project where state = ?";
        return queryForList(Project.class, sql,state);
    }

    @Override
    public List<Project> getProjectByContent(String content) {
        StringBuilder sql = new StringBuilder("select `id` , `name` , `type` , `state`,`clientId`,`content` from project where content like '%");
        sql.append(content).append("%'");
        return queryForList(Project.class, sql.toString());
    }
}
