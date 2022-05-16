package cn.servlet;

import cn.application.UserService;
import cn.domain.model.User;
import cn.infrastructure.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String userType = request.getParameter("userType");
		System.out.println("---------------"+userType);
		User user = new User(userName, password);
		// 调用 userService.login()登录处理业务
		User loginUser = userService.login(user,userType);
		// 如果等于null,说明登录 失败!
		if (loginUser == null) {
			// 把错误信息，和回显的表单项信息，保存到Request域中
			request.setAttribute("error", "用户名或密码错误！");
			request.setAttribute("userName", userName);
			//   跳回登录页面
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			// 登录 成功
			if("remember-me".equals(remember)) {
				WebUtils.rememberMe(userName, password, userType,response);
			} else {
				WebUtils.deleteCookie(userName, request, response);
			}
			session.setAttribute("currentUserType", userType);
			session.setAttribute("currentUser", user);
			request.setAttribute("mainPage", "page/admin/blank.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		}
	}

}
