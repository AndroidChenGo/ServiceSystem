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
		// ���� userService.login()��¼����ҵ��
		User loginUser = userService.login(user,userType);
		// �������null,˵����¼ ʧ��!
		if (loginUser == null) {
			// �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽Request����
			request.setAttribute("error", "�û������������");
			request.setAttribute("userName", userName);
			//   ���ص�¼ҳ��
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			// ��¼ �ɹ�
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
