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
		//request提供获取参数的方法geParmenter方法
		String weight = request.getParameter("weight");
		String height = request.getParameter("height");
		System.out.println(weight+","+height);
		//对于多选框,如果用户一个都不选,会返回null,当有多个请求参数名相同,应该使用geParamentValues()方法返回String[]数组
		String[] interset = request.getParameterValues("interset");
		if (interset != null) {
			for(String string : interset)
				System.out.println(string);
		}
		
		//计算bmi指数
		double bmi = Double.parseDouble(weight) / (Double.parseDouble(height) * Double.parseDouble(height));
		String state = "正常";
		if (bmi < 19) 
			state = "过轻";
		if(bmi > 25)
			state = "过重";
		
		/*这两行代码有两个作用:
		 * 1.设置content_type消息头,告诉浏览器服务器返回的数据类型以编码格式
		 * 2.设置out在输出时,使用哪种字符集来编码*/
		response.setContentType("text/html:charset = utf-8");
		PrintWriter printWriter = response.getWriter();
		printWriter.println(bmi);
	}
}
