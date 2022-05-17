package cn.servlet;

import cn.application.ProjectService;
import cn.domain.model.Project;
import cn.infrastructure.util.StringUtil;
import cn.infrastructure.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("txt/html;charset=utf-8");
        HttpSession session = request.getSession();
        Object currentUserType = session.getAttribute("currentUserType");
        String action = request.getParameter("action");
        if("list".equals(action)) {
            List<Project> projects = projectService.queryProjects();
            request.setAttribute("projectList", projects);
            request.setAttribute("mainPage", "page/admin/project.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("preSave".equals(action)){
            String projectId = request.getParameter("projectId");
            if(StringUtil.isNotEmpty(projectId)){
                Project project = projectService.queryProjectById(Integer.parseInt(projectId));
                request.setAttribute("project", project);
            }
            request.setAttribute("mainPage", "page/admin/projectSave.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("save".equals(action)){
            Project project = WebUtils.copyParamToBean(request.getParameterMap(),new Project());
            if(projectService.saveProject(project)){
                request.getRequestDispatcher("project?action=list").forward(request, response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                request.setAttribute("error", "clientId does not exist");
                request.setAttribute("project", project);
                request.setAttribute("mainPage", "page/admin/projectSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            };
        }else if("search".equals(action)){
            String searchText = request.getParameter("searchText");
            String searchType = request.getParameter("searchType");
            List<Project> projects = new ArrayList<>();
            if("name".equals(searchType)) {
                projects = projectService.queryProjectByName(searchText);
            } else if("id".equals(searchType)) {
                projects.add(projectService.queryProjectById(Integer.parseInt(searchText)));
            } else if("type".equals(searchType)) {
                projects = projectService.queryProjectByType(searchText);
            }else if("state".equals(searchType)) {
                projects = projectService.queryProjectByState(searchText);
            }else if("content".equals(searchType)) {
                projects = projectService.queryProjectByContent(searchText);
            }else if("clientId".equals(searchType)) {
                projects = projectService.queryProjectByClientId(Integer.parseInt(searchText));
            }
            request.setAttribute("searchText", searchText);
            request.setAttribute("searchType", searchType);
            //2 把全部项目保存到Request域中
            request.setAttribute("projectList", projects);
            //3、请求转发到project.jsp页面
            request.setAttribute("mainPage", "page/admin/project.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("delete".equals(action)){
            int id = WebUtils.parseInt(request.getParameter("projectId"), 0);
            projectService.deleteProjectById(id);
            request.getRequestDispatcher("project?action=list").forward(request, response);
        }

    }

}
