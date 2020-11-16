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
    	//通过几次GenericServle提供的方法来获取Servlet上下文
    	ServletContext servletContext = getServletContext();
    	//绑定数据
    	servletContext.setAttribute("user", "用户");
    	//获取session
    	HttpSession httpSession = req.getSession();
    	httpSession.setAttribute("role", "角色");
    	//设置session保鲜度30秒
    	httpSession.setMaxInactiveInterval(30);
    }

}
