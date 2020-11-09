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
	  //针对post有效
	  request.setCharacterEncoding("UTF-8");
	  //设置响应页面字符集
	  response.setContentType("text/html; charset = UTF-8");
	  
	  //读取用户信息
	  String username = request.getParameter("username");
	  String userPwd = request.getParameter("pwd");
	  String userEmail = request.getParameter("email");
	  String userPhone = request.getParameter("phone");
	  
	  //将user用户数据插入到数据库中
	  User user = new User();
	  user.setUserName(username);
	  user.setUserPwd(userPwd);
	  user.setUserEmail(userEmail);
	  user.setUserPhone(userPhone);
	  
	  //将User对象数据插入到数据库中
	  UserDao userDao = new UserDaoImpl();
	  int n = userDao.addUser(user);
	  if (n < 1) {
		throw new RuntimeException("系统维护,请稍后再试...");
	}
	  
	  PrintWriter pw = response.getWriter();
	  pw.println("添加成功");
	  pw.println("username: " + username + ",userPwd: " + userPwd + ",userEmail: " + userEmail + ",userPhone: " + userPhone);
	 }
}
