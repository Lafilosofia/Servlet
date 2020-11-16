package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class OneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//ͨ������GenericServle�ṩ�ķ�������ȡServlet������
    	ServletContext servletContext = getServletContext();
    	//������
    	servletContext.setAttribute("user", "�û�");
    	//��ȡsession
    	HttpSession httpSession = req.getSession();
    	httpSession.setAttribute("role", "��ɫ");
    	//����session���ʶ�30��
    	httpSession.setMaxInactiveInterval(30);
    }

}
