package cn.servlet;

import cn.application.UserService;
import cn.domain.model.User;
import cn.infrastructure.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        HttpSession session = req.getSession();
        String username = req.getParameter("userName");
        String password = req.getParameter("password");
        String userType = req.getParameter("userType");

        //2、检查 用户名是否可用
        if (userService.existsUsername(username,userType)) {
            System.out.println("用户名[" + username + "]已存在!");

            // 把回显信息，保存到Request域中
            req.setAttribute("error", "用户名已存在！！");
            req.setAttribute("userName", username);
            req.setAttribute("password", password);

            //跳回注册页面
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            // 可用
            //调用service保存到数据库
            userService.registUser(new User(username, password),userType);
            String remember = req.getParameter("remember");
            User user = new User(username, password);
            if("remember-me".equals(remember)) {
                WebUtils.rememberMe(username, password, userType,resp);
            } else {
                WebUtils.deleteCookie(username, req, resp);
            }
            session.setAttribute("currentUserType", userType);
            session.setAttribute("currentUser", user);
            req.setAttribute("mainPage", "page/admin/blank.jsp");
            req.getRequestDispatcher("mainAdmin.jsp").forward(req, resp);
            //跳到注册成功页面 regist_success.jsp
//            req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
        }

    }
}
