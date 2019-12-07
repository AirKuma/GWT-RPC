<%@page import="com.h4.Login"%>
<%@page import="java.util.List"%>
<%@page import="com.restfb.Connection"%>
<%@page import="com.restfb.types.User"%>
<%@page import="com.restfb.DefaultFacebookClient"%>
<%@page import="com.restfb.FacebookClient"%>
<%@page import="com.h4.URLFetcher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//取得授權碼
String code=request.getParameter("code");
//應用程式ID, 請換成自己申請的應用程式ID值
String client_id = "886918821370324";
//應用程式密鑰,請換成自己申請的密論
String secret = "5fd7cdd35f1441aa9833f1901a3efd36";
String redirect_uri = "http://cap-final.appspot.com/welcome2.jsp";  
String authURL = "https://graph.facebook.com/oauth/access_token?" +
	"client_id=" + client_id +
  "&scope=email,user_friends" +
	"&redirect_uri=" + redirect_uri +
	"&client_secret=" + secret +
	"&code=" + code;
String resp = URLFetcher.get(authURL);
String access_token = 
  resp.substring(resp.indexOf("=")+1, resp.lastIndexOf("&"));
%>
<%
String logoutURL = null;
FacebookClient fbClient = new DefaultFacebookClient(access_token);
User user = fbClient.fetchObject("me", User.class);

if (user == null) {
	response.sendRedirect("/login.jsp");
} else {
	logoutURL = "https://www.facebook.com/logout.php?access_token="+access_token+"&next=http://cap-final.appspot.com/login.jsp";
	Login member = new Login(user.getName(),user.getName(),
			user.getEmail());
	if (member.save()) {
		out.println("<p>[帳號新增完成]<p>");
	} else {
		out.println("<p>[已有帳號]<p>");
	}
	//response.sendRedirect("/H4.html");
}
%>
<a href="<%=logoutURL%>">登出FaceBook帳號</a>
<a href="H4.html">繼續</a>
</body>
</html>