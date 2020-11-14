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
		//����Cookie����
		Cookie cookie = new Cookie("username", "lhz");
		//��ӦCookie
		response.addCookie(cookie);
		//Cookie����ʱ��
		//1.Ĭ�������,�����ֻҪ���ر�,cookie�ͻ�һֱ����,������ر���cookie�ᱻɾ��
		//2.���Ե���setMaxAge()����������Cookie������ʱ��
		Cookie cookie2 = new Cookie("city", "xi'an");
		//����cookie
		cookie2.setMaxAge(365 * 24 * 60 * 60);
		response.addCookie(cookie2);
		//ɾ��cookie,����ɾ��һ������Ϊcity��cookie
		cookie2.setMaxAge(0);
		response.addCookie(cookie2);
		
		//URLEncoder�����һ��encode��̬����,�����Խ���ͨ�ַ���ת��Ϊ�ַ���
		//URLDecode�����һ��decode��̬����,�����Խ�����ȥ������ַ���ת��Ϊ�����ַ�
		String string = "�����";
		string = URLEncoder.encode("�����");
		Cookie cookie3 = new Cookie("student", string);
		response.addCookie(cookie3);
		
		
	}
}
