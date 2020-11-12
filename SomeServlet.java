package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SomeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		System.out.println(name);
		//重定向
		String path = request.getContextPath();
		System.out.println(path);
		//response.sendRedirect(path + "/biz/b1.jsp");
		//转发(错误方式,转发中不要以应用名直接写在路径里面)
		request.getRequestDispatcher("biz/b1.jsp").forward(request, response);
	}
}
