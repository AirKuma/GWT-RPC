<%@page import="com.h4.Login"%>
<%@page import="com.h4.PMF"%>
<%@page import="javax.jdo.PersistenceManager"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome Page</title>
</head>
<body>
	
	<div id="wherethegwtwidgetgoes"> 
	</div> 
<% 
        String logoutURL = null;
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user == null) {
			response.sendRedirect("/login.jsp");
		} else {
			logoutURL = userService.createLogoutURL("/login.jsp");
			Login member = new Login(user.getNickname(),user.getNickname(),
					user.getEmail());
			if (member.save()) {
				out.println("<p>[帳號新增完成]<p>");
			} else {
				out.println("<p>[已有帳號]<p>");
			}
			//response.sendRedirect("/H4.html");
		}
	%>
	<a href="<%=logoutURL%>">登出Google帳號</a>
	<a href="H4.html">繼續</a>
</body>
</html>