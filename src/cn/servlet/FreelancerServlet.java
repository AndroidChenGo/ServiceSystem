package cn.servlet;

import cn.application.FreelancerService;
import cn.domain.model.Client;
import cn.domain.model.Freelancer;
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

public class FreelancerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FreelancerService freelancerService = new FreelancerService();

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
        response.setContentType("application/json;charset=utf-8");
        HttpSession session = request.getSession();
        Object currentUserType = session.getAttribute("currentUserType");
        String action = request.getParameter("action");
        if("list".equals(action)) {
            //1 通过FreelancerService查询全部自由职业者
            List<Freelancer> freelancers = freelancerService.queryFreelancers();
            //2 把全部自由职业者保存到Request域中
            request.setAttribute("userList", freelancers);
            //3、请求转发到freelancer.jsp页面
            request.setAttribute("mainPage", "page/admin/freelancer.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("preSave".equals(action)){
            String userId = request.getParameter("userId");
            if(StringUtil.isNotEmpty(userId)){
                Freelancer freelancer = freelancerService.queryFreelancerById(Integer.parseInt(userId));
                request.setAttribute("user", freelancer);
            }
            request.setAttribute("mainPage", "page/admin/freelancerSave.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("save".equals(action)){
            Freelancer freelancer = WebUtils.copyParamToBean(request.getParameterMap(),new Freelancer());
            if(freelancerService.saveFreelancer(freelancer)){
                request.getRequestDispatcher("freelancer?action=list").forward(request, response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                request.setAttribute("error", "id is repeated");
                request.setAttribute("user", freelancer);
                request.setAttribute("mainPage", "page/admin/freelancerSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            };
        }else if("search".equals(action)){
            String searchText = request.getParameter("searchText");
            String searchType = request.getParameter("searchType");
            List<Freelancer> freelancers = new ArrayList<>();
            if("name".equals(searchType)) {
                freelancers = freelancerService.queryFreelancerByName(searchText);
            } else if("id".equals(searchType)) {
                freelancers.add(freelancerService.queryFreelancerById(Integer.parseInt(searchText)));
            } else if("type".equals(searchType)) {
                freelancers = freelancerService.queryFreelancerByType(searchText);
            } else if("introduction".equals(searchType)) {
                freelancers = freelancerService.queryFreelancerByIntroduction(searchText);
            }
            request.setAttribute("searchText", searchText);
            request.setAttribute("searchType", searchType);
            //2 把全部自由职业者保存到Request域中
            request.setAttribute("userList", freelancers);
            //3、请求转发到freelancer.jsp页面
            request.setAttribute("mainPage", "page/admin/freelancer.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("delete".equals(action)){
            int id = WebUtils.parseInt(request.getParameter("userId"), 0);
            freelancerService.deleteFreelancerById(id);
            request.getRequestDispatcher("freelancer?action=list").forward(request, response);
        }

    }

}
