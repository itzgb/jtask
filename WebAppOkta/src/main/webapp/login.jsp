<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1"><script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>

<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
     content="294034406382-3e4t07ddv61g9c63mt6l31ap64lt2b9a.apps.googleusercontent.com">
<title>Login</title>
</head>
<body>
	<form method = "post" action="login">
		<table align="center">
			<tr><td>Login</td></tr>
			<tr>
				<td>Username:</td>
				<td><input type ="text" name="username"></td>
			</tr>
				<td>Password:</td>
				<td><input type ="password" name="password"></td>
			<tr>
			</tr>
				<td><input type = "submit" value = "submit"></td>
			<tr>
				<td>Already a Member?</td><td> <a href ="register.jsp">Register</a></td>
			</tr>
		</table>
	</form>
	
	<br/>
	<div align = center>
		or Sign in using Google
		<div class="g-signin2" data-onsuccess="onSignIn"></div>
	
	</div>
	


	<script>

	
	
	
      //google callback. This function will redirect to our login servlet
      function onSignIn(googleUser) {
         var profile = googleUser.getBasicProfile();
         console.log('ID: ' + profile.getId());
         console.log('Name: ' + profile.getName());
         console.log('Image URL: ' + profile.getImageUrl());
         console.log('Email: ' + profile.getEmail());
         console.log('id_token: ' + googleUser.getAuthResponse().id_token);

         //do not post all above info to the server because that is not secure.
         //just send the id_token

         var redirectUrl = '/WebAppOkta/onSignIn';

         //using jquery to post data dynamically
         var form = $('<form action="' + redirectUrl + '" method="post">' +
                          '<input type="text" name="id_token" value="' +
                           googleUser.getAuthResponse().id_token + '" />' +
                                                                '</form>');
         $('body').append(form);
         form.submit();
      }

   </script>
</body>
</html>