<%@ page import="registration.UserDAO" %>
<%@ page import="java.io.IOException" %>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("email");
    String email = request.getParameter("password");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");

    UserDAO userDAO = new UserDAO(); // create instance
    boolean Valid = userDAO.registerUser(username, email, password, phone, address);
    out.println(Valid);
    if (Valid) {
       response.sendRedirect("usertype.html");
    } else {
       response.sendRedirect("errorpage.html");
    }
%>