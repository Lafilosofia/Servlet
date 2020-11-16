package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.dgc.VMID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BmiServlet extends HttpServlet {
	private double min;
	private double max;
	//获取初始化参数
	@Override
	public void init(){
		String strMin = getInitParameter("min");
		String strMax = getInitParameter("max");
		min = Double.parseDouble(strMin);
		max = Double.parseDouble(strMax);
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double height = Double.parseDouble(request.getParameter("height"));
		double weight = Double.parseDouble(request.getParameter("weight"));
		
		double bmi = weight / (height * height);
		
		String state = "正常";
		if (bmi < min) {
			state = "过轻";
		}
		if (bmi > max) {
			state = "过重";
		}
		response.setContentType("text/html:charset = utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(bmi);
	}
}
