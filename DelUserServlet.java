package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;

public class DelUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8");
		PrintWriter printWriter = response.getWriter();
		//ɾ���û�������Ϣ
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao userDao = new UserDaoImpl();
		int v = userDao.delUser(id);
		if (v > 0) {
			printWriter.println("ɾ���ɹ�");
		}else
		printWriter.println("ɾ��ʧ��");
	}
}
