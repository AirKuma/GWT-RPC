<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帳號登入</title>
</head>
<body>
	<%
		String loginURL = null;
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if (user != null) {
			response.sendRedirect("/welcome.jsp");   
		} else {
			loginURL = userService.createLoginURL("/login.jsp");
		}
	%>
	<%
String fbLoginURL = "https://www.facebook.com/dialog/oauth?" +
		"client_id=886918821370324" +
    "&scope=email,user_friends" +
		"&redirect_uri=http://cap-final.appspot.com/welcome2.jsp";   
%>
	<table>
		<tr>
			<td colspan="1"><a href="<%=loginURL%>">Google帳號登入</a></td>
			<td colspan="1"><a href="<%= fbLoginURL%>">Facebook登入</a></td>
		</tr>
	</table>
</body>
</html>