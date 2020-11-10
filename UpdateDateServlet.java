package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UpdateDateServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求字符集
		request.setCharacterEncoding("utf-8");
		//获取参数
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		//把用户数据封装到User对象中
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPhone(phone);
		//修改用户数据
		UserDao userDao = new UserDaoImpl();
		int n = userDao.updateUserDate(user);
		if (n < 1) {
			request.setAttribute("message", "会员特权,请充值再试!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return ;
		}
		//转到list
		request.getRequestDispatcher("list").forward(request, response);;
	}
}
