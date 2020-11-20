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
		//�ж�������û���¼·����ֱ��ͨ��
		String uri = request2.getRequestURI();
		if (uri.endsWith("login.do") || uri.endsWith("add.do")) {
			chain.doFilter(request2, response2);
			return;
		}
		//������������·��,����ȡsession���󶨵�ֵ��λnull,��ͨ��,�����߳���½ҳ��
		HttpSession session = request2.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {		//����ͨ��
			//�ض��򵽵�¼ҳ��(�ض������ʹ�þ���·��)
			response2.sendRedirect(request2.getContextPath() + "/login.jsp");
		}else {		//ͨ��
			chain.doFilter(request2, response2);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
