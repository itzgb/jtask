<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Enter the OTP;
	<form method ="post" action="/WebAppOkta/tfaCheck">
		<input type ="text" name = "otp">
		<input type = "submit" value ="verify">
	</form>
	
</body>
</html>