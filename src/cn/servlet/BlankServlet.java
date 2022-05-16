package cn.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlankServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Object currentUserType = session.getAttribute("currentUserType");
		if("admin".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "page/admin/blank.jsp");
			request.getRequestDispatcher("mainAdmin.jsp").forward(request, response);
		} else if("freelancer".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "page/freelancer/blank.jsp");
			request.getRequestDispatcher("mainFreelancer.jsp").forward(request, response);
		} else if("client".equals((String)currentUserType)) {
			request.setAttribute("mainPage", "page/client/blank.jsp");
			request.getRequestDispatcher("mainClient.jsp").forward(request, response);
		}
	}

	
}
