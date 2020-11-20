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
		//��ȡ������Դ����·��
		//System.out.println(request.getContentLength());
		//��ȡ������Դ·��
		//System.out.println(request.getRequestURI());
		//��ȡ������Դ����·��
		//System.out.println(request.getRequestURL());
		//��ȡ��ʵ·��
		//System.out.println(request.getServletContext().getRealPath("/"));
		String uri = request.getRequestURI();
		//uri = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("uri");
		/*if ("/add".equals(uri)) {
			//�������ҵ��
			System.out.println("���ҵ��");
		}else if("/find".equals(uri)){
			//�����ѯҵ��
			System.out.println("��ѯҵ��");
		}*/
		
		
		//���������ַ��ѯ��ͬ������
		if(uri.endsWith("add.do")){
			//����û�ҵ��
			AddUserServletAcion(request,response);
		}else if(uri.endsWith("list.do")){
			//��ѯ�û�ҵ��
			FindUserServletAction(request,response);
		}else if(uri.endsWith("del.do")){
			//ɾ���û�����ҵ��
			DelUserServletAction(request,response);
		}else if (uri.endsWith("login.do")) {
			//�û���¼ҵ��
			LoginUserServletAction(request,response);
		}else if(uri.endsWith("userById.do")){
			//����id��������ҵ��
			UpdateUserServletAction(request,response);
		}else if(uri.endsWith("update.do")){
			//�޸������û�ҵ��
			UpdateDateServletAction(request,response);
		}
	}

	
	private void UpdateDateServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				request.getRequestDispatcher("list.do").forward(request, response);;
		
	}


	private void UpdateUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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


	private void LoginUserServletAction(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset = UTF-8");
			
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			
			String string = "Pentakill";
			String md5 = DigestUtils.md5Hex(pwd + string);
			
			if (username == null || username.trim().isEmpty()) {
				request.setAttribute("meg", "�û�������Ϊ��!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			UserDao userDao = new UserDaoImpl();
			int count = userDao.findUserByName(username);
			if (count < 1) {
				request.setAttribute("meg", "�û���������");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
			
			User user = userDao.selectUserByName(username, md5);
			if (user == null) {
				request.setAttribute("megs", "���벻��ȷ");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			//��session������
			request.getSession().setAttribute("user", user);
			
			//��¼�ɹ�ת���˺�����ҳ��
			request.getRequestDispatcher("list.do").forward(request, response);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void DelUserServletAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset = utf-8");
		PrintWriter printWriter = response.getWriter();
		//ɾ���û�������Ϣ
		int id = Integer.parseInt(request.getParameter("id"));
		UserDao userDao = new UserDaoImpl();
		int v = userDao.delUser(id);
		if (v > 0) {
			response.sendRedirect("list.do");
		}else
		printWriter.println("ɾ��ʧ��");	
	}
	
	

	private void FindUserServletAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//������Ӧ�����ַ���
			response.setContentType("text/html;charset = utf-8");
			//ͨ��httpservletresponse��ȡ�����
			PrintWriter printWriter = response.getWriter();
			//��ѯ�����û�����Ϣ
			UserDao userDao = new UserDaoImpl();
			List<User> list = userDao.findUserAll();
			
			//��request������
			request.setAttribute("list", list);
			
			//��ȡrequest����ָ��������
			//List<User> listUser = (List<User>)request.getAttribute("list");
			//System.out.println(listUser);
			
			//��ȡת����
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("listUsers.jsp");
			//ת��
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("massage", "ϵͳ��æ,���Ժ�����");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

	private void AddUserServletAcion(HttpServletRequest request, HttpServletResponse response) throws IOException{
		try {
			  //���post��Ч
			  request.setCharacterEncoding("UTF-8");
			  //������Ӧҳ���ַ���
			  response.setContentType("text/html; charset = UTF-8");
			  
			  //��ȡ�û���Ϣ
			  String username = request.getParameter("username");
			  String userPwd = request.getParameter("pwd");
			  String userEmail = request.getParameter("email");
			  String userPhone = request.getParameter("phone");
			  
			  //�û�������Ϊ��
			  if (username == null || username.trim().isEmpty()) {
				request.setAttribute("meg", "�û�������Ϊ��");
				//��ȡת����ת��
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return;
			}
			  
			  //�û�����ռ��
			  UserDao userDao = new UserDaoImpl();
			  int count = userDao.findUserByName(username);
			  if (count > 0) {
				request.setAttribute("meg", "�û����ѱ�ռ��");
				//��ȡת����ת��
				request.getRequestDispatcher("addUser.jsp").forward(request, response);
				return;
			}
			  
			  
			  //��
			  String string = "Pentakill";
			  //�ļ�ժҪ���Ҹ�ԭʼ�������
			  String md5 = DigestUtils.md5Hex(userPwd + string);
			  
			  
			  //��user�û����ݲ��뵽���ݿ��� 
			  User user = new User();
			  user.setUserName(username);
			  user.setUserPwd(md5);
			  user.setUserEmail(userEmail);
			  user.setUserPhone(userPhone);
			  
			  //��User�������ݲ��뵽���ݿ���
			  int n = userDao.addUser(user); 
			  if (n < 1) {
				  request.setAttribute("massage", "ϵͳ��æ,���Ժ�����");
					request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			  
			  //��ȡ�����
			  PrintWriter pw = response.getWriter();
			  pw.println("��ӳɹ�");
			  pw.println("username: " + username + ",userPwd: " + userPwd + ",userEmail: " + userEmail + ",userPhone: " + userPhone);
			  
			  //httpservletrequest�ṩ���ض��򷽷� sendRedirect
			  response.sendRedirect("login.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
