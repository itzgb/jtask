<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Okta.oktaUser"%>
<%@page import="java.util.ArrayList"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
	<%
	 ArrayList<oktaUser> userlist=(ArrayList<oktaUser>) session.getAttribute("userlist"); 
	  for (oktaUser user: userlist) { 
	%>
		
		
			<tr>
				<td>FirstName: = </td><td><%=user.getFirstName()%></td>
				<td>LastName:</td><td><%=user.getLastName()%></td>
				<td>Email</td><td><%=user.getEmail()%></td>
			</tr>
	<%} %>
		</table>
</body>
</html>