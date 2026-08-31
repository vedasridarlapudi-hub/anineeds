<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
<head><title>Home</title></head>
<body>
    <h2>Welcome, <%= session.getAttribute("user") %>!</h2>
    <a href="LogoutServlet">Logout</a>
</body>
</html>