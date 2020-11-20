package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class ActionServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取请求资源绝对路径
		//System.out.println(request.getContentLength());
		//获取请求资源路径
		//System.out.println(request.getRequestURI());
		//获取请求资源完整路径
		//System.out.println(request.getRequestURL());
		//获取真实路径
		//System.out.println(request.getServletContext().getRealPath("/"));
		String uri = request.getRequestURI();
		//uri = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("uri");
		/*if ("/add".equals(uri)) {
			//处理添加业务
			System.out.println("添加业务");
		}else if("/find".equals(uri)){
			//处理查询业务
			System.out.println("查询业务");
		}*/
		
		
		//根据请求地址查询不同的数据
		if(uri.endsWith("add.do")){
			//添加用户业务
			AddUserServletAcion(request,response);
		}else if(uri.endsWith("list.do")){
			//查询用户业务
			FindUserServletAction(request,response);
		}else if(uri.endsWith("del.do")){
			//删除用户数据业务
			DelUserServletAction(request,response);
		}else if (uri.endsWith("login.do")) {
			//用户登录业务
			LoginUserServletAction(request,response);
		}else if(uri.endsWith("userById.do")){
			//根据id更改数据业务
			UpdateUserServletAction(request,response);
		}else if(uri.endsWith("update.do")){
			//修改数据用户业务
			UpdateDateServletAction(request,response);
		}
	}

	
	private void UpdateDateServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				request.getRequestDispatcher("list.do").forward(request, response);;
		
	}


	private void UpdateUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


	private void LoginUserServletAction(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset = UTF-8");
			
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			
			String string = "Pentakill";
			String md5 = DigestUtils.md5Hex(pwd + string);
			
			if (username == null || username.trim().isEmpty()) {
				request.setAttribute("meg", "用户名不能为空!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			UserDao userDao = new UserDaoImpl();
			int count = userDao.findUserByName(username);
			if (count < 1) {
				request.setAttribute("meg", "用户名不存在");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			
			User user = userDao.selectUserByName(username, md5);
			if (user == null) {
				request.setAttribute("megs", "密码不正确");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			//用session绑定数据
			request.getSession().setAttribute("user", user);
			
			//登录成功转到账号密码页面
			request.getRequestDispatcher("list.do").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void DelUserServletAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset = utf-8");
		PrintWriter printWriter = response.getWriter();
		//删除用户所有信息
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao userDao = new UserDaoImpl();
		int v = userDao.delUser(id);
		if (v > 0) {
			response.sendRedirect("list.do");
		}else
		printWriter.println("删除失败");	
	}
	
	

	private void FindUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//设置响应类型字符集
			response.setContentType("text/html;charset = utf-8");
			//通过httpservletresponse获取输出流
			PrintWriter printWriter = response.getWriter();
			//查询所有用户的信息
			UserDao userDao = new UserDaoImpl();
			List<User> list = userDao.findUserAll();
			
			//用request绑定数据
			request.setAttribute("list", list);
			
			//获取request上所指定的数据
			//List<User> listUser = (List<User>)request.getAttribute("list");
			//System.out.println(listUser);
			
			//获取转发器
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("listUsers.jsp");
			//转发
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("massage", "系统繁忙,请稍后再试");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	private void AddUserServletAcion(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			  //针对post有效
			  request.setCharacterEncoding("UTF-8");
			  //设置响应页面字符集
			  response.setContentType("text/html; charset = UTF-8");
			  
			  //读取用户信息
			  String username = request.getParameter("username");
			  String userPwd = request.getParameter("pwd");
			  String userEmail = request.getParameter("email");
			  String userPhone = request.getParameter("phone");
			  
			  //用户名不能为空
			  if (username == null || username.trim().isEmpty()) {
				request.setAttribute("meg", "用户名不能为空");
				//获取转发器转发
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return;
			}
			  
			  //用户名被占用
			  UserDao userDao = new UserDaoImpl();
			  int count = userDao.findUserByName(username);
			  if (count > 0) {
				request.setAttribute("meg", "用户名已被占用");
				//获取转发器转发
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return;
			}
			  
			  
			  //盐
			  String string = "Pentakill";
			  //文件摘要并且给原始密码加密
			  String md5 = DigestUtils.md5Hex(userPwd + string);
			  
			  
			  //将user用户数据插入到数据库中 
			  User user = new User();
			  user.setUserName(username);
			  user.setUserPwd(md5);
			  user.setUserEmail(userEmail);
			  user.setUserPhone(userPhone);
			  
			  //将User对象数据插入到数据库中
			  int n = userDao.addUser(user); 
			  if (n < 1) {
				  request.setAttribute("massage", "系统繁忙,请稍后再试");
					request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			  
			  //获取输出流
			  PrintWriter pw = response.getWriter();
			  pw.println("添加成功");
			  pw.println("username: " + username + ",userPwd: " + userPwd + ",userEmail: " + userEmail + ",userPhone: " + userPhone);
			  
			  //httpservletrequest提供了重定向方法 sendRedirect
			  response.sendRedirect("login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
