package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;

public class FilterServlet implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest)request;
		HttpServletResponse response2 = (HttpServletResponse)response;
		//判断如果是用户登录路径则直接通过
		String uri = request2.getRequestURI();
		if (uri.endsWith("login.do") || uri.endsWith("add.do")) {
			chain.doFilter(request2, response2);
			return;
		}
		//过滤所有请求路径,若获取session所绑定的值部位null,则通过,否则踢出登陆页面
		HttpSession session = request2.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {		//不能通过
			//重定向到登录页面(重定向最好使用绝对路径)
			response2.sendRedirect(request2.getContextPath() + "/login.jsp");
		}else {		//通过
			chain.doFilter(request2, response2);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
