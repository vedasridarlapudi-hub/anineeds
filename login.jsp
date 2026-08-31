<%@ page import="auth.UserAuthentication" %>
<%@ page import="java.io.IOException" %>
//login.jsp
<%
    // Get user inputs from HTML form
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // Call Java authentication logic
    boolean isValidUser = UserAuthentication.validateUser(username, password);

    if (isValidUser) {
        response.sendRedirect("welcome.jsp?username=" +username);
    } else {
        response.sendRedirect("errorpage.html");
    }
%>