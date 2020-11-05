package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BmiServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException{
		//request�ṩ��ȡ�����ķ���geParmenter����
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		System.out.println(weight+","+height);
		//���ڶ�ѡ��,����û�һ������ѡ,�᷵��null,���ж�������������ͬ,Ӧ��ʹ��geParamentValues()��������String[]����
		String[] interset = request.getParameterValues("interset");
		if (interset != null) {
			for(String string : interset)
				System.out.println(string);
		}
		
		//����bmiָ��
		double bmi = Double.parseDouble(weight) / (Double.parseDouble(height) * Double.parseDouble(height));
		String state = "����";
		if (bmi < 19) 
			state = "����";
		if(bmi > 25)
			state = "����";
		
		/*�����д�������������:
		 * 1.����content_type��Ϣͷ,������������������ص����������Ա����ʽ
		 * 2.����out�����ʱ,ʹ�������ַ���������*/
		response.setContentType("text/html:charset = utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(bmi);
	}
}
