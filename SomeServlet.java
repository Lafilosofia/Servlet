package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SomeServlet
 */
public class SomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int count;  
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	synchronized (this) {	//Í¬²¿Êý
			count ++;
			System.out.println(count);
		}
    }

}
