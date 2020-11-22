<%@page import="bean.Address"%>
<%@page import="bean.Employyee"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.*"%>
<%@page import="bean.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("username", "admin");
		session.setAttribute("username", "admin2");
		application.setAttribute("username", "admin3");
		pageContext.setAttribute("username", "admin4");
	%>
	<!-- 使用el表达式获取绑定数据 -->
	获取数据: ${username}
	
	<br>
	
	<!-- 通过域对象获取指定的绑定数据 -->
	${sessionScope.username }
	
	<%
	User user = new User();
	user.setUserName("马保国");
	user.setAge(69);
	request.setAttribute("user", user);
	%>
	<br>
	
	<%
	User u = (User)request.getAttribute("user");
	String name = u.getUserName();
	int age = u.getAge();
	out.print(name + "," + age);
	
	%>
	
	<!-- 用el表达式 -->
	获取user对象:${user }
	获取user名字:${user.userName }
	获取user年龄:${user.age }
	
	Map集合<br>
	<%
	Map<String,Object> map = new HashMap();
	map.put("user", user);
	map.put("page", 2);
	pageContext.setAttribute("map", map);
	%>
	
	<%
	Map<String,Object> m = (Map<String,Object>)pageContext.getAttribute("map");
	Set<Entry<String,Object>> entry = m.entrySet();
	for(Entry<String,Object> e : entry){
		out.print(e.getKey());
		out.print(e.getValue());
	}
	%>
	
	<!-- 用el表达式写 -->
	<br>
	获取map中的user对象:${map.user}<br>
	获取map中的page:${map.page }<br>
	获取map中的user对象中的名字:${map.user.userName }<br>
	获取map中的user对象中的年龄:${map.user.age }<br>
	
	<%
	Employyee employyee = new Employyee();
	employyee.setAge(69);
	employyee.setName("马保国");
	employyee.setSalary(10);
	
	Address address = new Address();
	address.setArea("临潼");
	address.setCity("西安");
	//employyee.setAddress(address);
	
	request.setAttribute("employyee", employyee);
	%>
	
	<!-- el表达式 -->
	用户年龄:${employyee.age }<br>
	用户名:${employyee.ename }<br>
	用户工资:${employyee.salary }<br>
	用户地址区域:${employyee.address.area }
	用户地址城市:${employyee.address.city }
</body>
</html>