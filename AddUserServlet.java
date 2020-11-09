package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class AddUserServlet extends HttpServlet {
	@Override
	 protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //���post��Ч
	  request.setCharacterEncoding("UTF-8");
	  //������Ӧҳ���ַ���
	  response.setContentType("text/html; charset = UTF-8");
	  
	  //��ȡ�û���Ϣ
	  String username = request.getParameter("username");
	  String userPwd = request.getParameter("pwd");
	  String userEmail = request.getParameter("email");
	  String userPhone = request.getParameter("phone");
	  
	  //��user�û����ݲ��뵽���ݿ���
	  User user = new User();
	  user.setUserName(username);
	  user.setUserPwd(userPwd);
	  user.setUserEmail(userEmail);
	  user.setUserPhone(userPhone);
	  
	  //��User�������ݲ��뵽���ݿ���
	  UserDao userDao = new UserDaoImpl();
	  int n = userDao.addUser(user);
	  if (n < 1) {
		throw new RuntimeException("ϵͳά��,���Ժ�����...");
	}
	  
	  PrintWriter pw = response.getWriter();
	  pw.println("��ӳɹ�");
	  pw.println("username: " + username + ",userPwd: " + userPwd + ",userEmail: " + userEmail + ",userPhone: " + userPhone);
	 }
}
