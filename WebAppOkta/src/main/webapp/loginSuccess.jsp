<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="google-signin-client_id"
     content="294034406382-3e4t07ddv61g9c63mt6l31ap64lt2b9a.apps.googleusercontent.com">
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>


<title>Insert title here</title>
</head>
<body>
	Hello ,  <%
	
	String user = (String)session.getAttribute("user");
	out.print(user);
	%>
	You Have Logged in Successfully 
	<% if((Boolean)session.getAttribute("gsignin")!= true){
	%>
	<a href = /WebAppOkta/logout>Logout</a>
	<%}else{ %>
	<a href="login.jsp" onclick="signOut();">Sign out</a>
	<%} %>
	Two Factor Authentication Status : 
	<%String status = (String)session.getAttribute("tfa");
	if(status==null){
		out.print("false");}
	else{out.print(status);}
	
	if(status == null){%>
		<a href ="/WebAppOkta/enable">Enable</a>
	<% }%>
	<br/>
	<br/>
	<br/>
	<% if((String)session.getAttribute("err") != null){%>
		<b>Duplication Found. Enter New Name</b>
	<% }%>
	<form method = "post" action="/WebAppOkta/createaccokta">
		<table align="center">
			<tr><td>Create an account</td></tr>
			<tr>
				<td>FirstName:</td>
				<td><input type ="text" name="firstname"></td>
			</tr>
			<tr>
				<td>LastName:</td>
				<td><input type ="text" name="lastname"></td>
			</tr>
			<tr>
				<td>email:</td>
				<td><input type ="text" name="email"></td>
			</tr>
			<tr>
				<td><input type = "submit" value = "submit"></td>
			</tr>
		</table>
	</form>
	
	<a href = "/WebAppOkta/viewOkta">view existing users</a>
	
	
	
	<script>
	
	function init() {
		  gapi.load('auth2', function() {
		    /* Ready. Make a call to gapi.auth2.init or some other API */
		    gapi.auth2.init();
		  });
		}

	function signOut() {
	    var auth2 = gapi.auth2.getAuthInstance();
	    auth2.signOut().then(function () {
	      console.log('User signed out.');
	    });
	  }

	</script>
</body>
</html>