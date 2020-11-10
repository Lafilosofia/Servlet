package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取参数
		String str = request.getParameter("id");
		//字符串转换int类型
		int id = Integer.parseInt(str);
		//创建UserDaoImpl对象,调用接口中的方法
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findUserById(id);
		//绑定user对象,获取转发器并转发到updateUser.jsp
		request.setAttribute("user", user);
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}
}
