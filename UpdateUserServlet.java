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
		//��ȡ����
		String str = request.getParameter("id");
		//�ַ���ת��int����
		int id = Integer.parseInt(str);
		//����UserDaoImpl����,���ýӿ��еķ���
		UserDao userDao = new UserDaoImpl();
		User user = userDao.findUserById(id);
		//��user����,��ȡת������ת����updateUser.jsp
		request.setAttribute("user", user);
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}
}
