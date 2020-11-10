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
		//���������ַ���
		request.setCharacterEncoding("utf-8");
		//��ȡ����
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		//���û����ݷ�װ��User������
		User user = new User();
		user.setId(id);
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPhone(phone);
		//�޸��û�����
		UserDao userDao = new UserDaoImpl();
		int n = userDao.updateUserDate(user);
		if (n < 1) {
			request.setAttribute("message", "��Ա��Ȩ,���ֵ����!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			return ;
		}
		//ת��list
		request.getRequestDispatcher("list").forward(request, response);;
	}
}
