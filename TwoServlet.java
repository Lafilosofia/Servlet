package web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class TwoServlet
 */
public class TwoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//��ȡservlet���������󶨵�����
    	ServletContext servletContext = getServletContext();
    	String scName = (String)servletContext.getAttribute("user");
    	System.out.println("Servlet���󶨵�������" + scName);
    	
    	//��ȡsession���󶨵�����
    	HttpSession httpSession = req.getSession();
    	String seName = (String)httpSession.getAttribute("role");
    	System.out.println("Session���󶨵�������" + seName);
    }

}
