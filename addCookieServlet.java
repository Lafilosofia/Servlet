package web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建Cookie对象
		Cookie cookie = new Cookie("username", "lhz");
		//响应Cookie
		response.addCookie(cookie);
		//Cookie生存时间
		//1.默认情况下,浏览器只要不关闭,cookie就会一直存在,浏览器关闭则cookie会被删除
		//2.可以调用setMaxAge()方法来设置Cookie的生存时间
		Cookie cookie2 = new Cookie("city", "xi'an");
		//设置cookie
		cookie2.setMaxAge(365 * 24 * 60 * 60);
		response.addCookie(cookie2);
		//删除cookie,比如删除一个名称为city的cookie
		cookie2.setMaxAge(0);
		response.addCookie(cookie2);
		
		//URLEncoder类包含一个encode静态方法,它可以将普通字符串转换为字符串
		//URLDecode类包含一个decode静态方法,它可以将看上去乱码的字符串转化为特殊字符
		String string = "李浩哲";
		string = URLEncoder.encode("李浩哲");
		Cookie cookie3 = new Cookie("student", string);
		response.addCookie(cookie3);
		
		
	}
}
