package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		/*
		 * request提供了setCharacterEncoding(String charset);
		 * 该方法针对post请求有效
		 */
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("username");
		String pwd = request.getParameter("password");
		String state = "";
		if ("admin".equals(name) && "123456".equals(pwd)) {
			state = "成功";
		}else {
			state = "失败";
		}
		PrintWriter pWriter = response.getWriter();
		response.setContentType("text/hml:charset = utf-8");
		pWriter.println(state);
	}
}
