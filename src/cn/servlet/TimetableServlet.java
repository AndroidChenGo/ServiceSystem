package cn.servlet;

import cn.application.TimetableService;
import cn.domain.model.Project;
import cn.domain.model.Timetable;
import cn.infrastructure.util.StringUtil;
import cn.infrastructure.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimetableServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TimetableService timetableService = new TimetableService();

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
            List<Timetable> timetables = timetableService.queryTimetables();
            request.setAttribute("timetableList", timetables);
            request.setAttribute("mainPage", "page/admin/timetable.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("preSave".equals(action)){
            String id = request.getParameter("timetableId");
            if(StringUtil.isNotEmpty(id)){
                Timetable timetable = timetableService.queryTimetableById(Integer.parseInt(id));
                request.setAttribute("timetable", timetable);
            }
            request.setAttribute("mainPage", "page/admin/timetableSave.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("save".equals(action)){
            Timetable timetable = WebUtils.getTimetable(request);
//            Timetable timetable = WebUtils.copyParamToBean(request.getParameterMap(),new Timetable());
            if(timetableService.saveTimetable(timetable)){
                request.getRequestDispatcher("timetable?action=list").forward(request, response);
            }else{
                response.setContentType("application/json;charset=utf-8");
                request.setAttribute("error", "freelancerId does not exist");
                request.setAttribute("timetable", timetable);
                request.setAttribute("mainPage", "page/admin/timetableSave.jsp");
                request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
            };
        }else if("search".equals(action)){
            String searchText = request.getParameter("searchText");
            String searchType = request.getParameter("searchType");
            List<Timetable> timetables = new ArrayList<>();
            if("name".equals(searchType)) {
                timetables = timetableService.queryTimetableByName(searchText);
            } else if("id".equals(searchType)) {
                timetables.add(timetableService.queryTimetableById(Integer.parseInt(searchText)));
            }else if("content".equals(searchType)) {
                timetables = timetableService.queryTimetableByContent(searchText);
            }else if("freelancerId".equals(searchType)) {
                timetables = timetableService.queryTimetableByFreelancerId(Integer.parseInt(searchText));
            }
            request.setAttribute("timetableList", timetables);
            request.setAttribute("mainPage", "page/admin/timetable.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("searchTime".equals(action)){
            String searchTime = request.getParameter("searchTime");
            if(StringUtil.isNotEmpty(searchTime)){
                request.getRequestDispatcher("timetable?action=list").forward(request, response);
            }
            List<Timetable> timetables = timetableService.queryTimetableByTime(searchTime);
            request.setAttribute("timetableList", timetables);
            request.setAttribute("mainPage", "page/admin/timetable.jsp");
            request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
        }else if("delete".equals(action)){
            int id = WebUtils.parseInt(request.getParameter("timetableId"), 0);
            timetableService.deleteProjectById(id);
            request.getRequestDispatcher("timetable?action=list").forward(request, response);
        }

    }

}
