<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Sign Up</title>
	<link rel="stylesheet" href="SignUp.css">
</head>
<body>
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		
		<div class="info-section">
			<a href="Login.jsp">Login</a> | <a href="SignUp.jsp">Sign up</a>
		</div>
	</div>
	<br>
	<div class="container">
		<h1>Sign Up</h1>
		<form id="Sign-up-form" action = "signup" method="post">
			<label for="username">Username:</label>
			<input type="text" id="username" name="username" maxlength="255"><br>
			<label for="username">Name:</label>
			<input type="text" id="name" name="name" maxlength="255"><br>
			<label for="email">Email:</label>
			<input type="email" id="email" name="email" maxlength="255"><br>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" maxlength="255"><br>
			<label for="account-type">Account Type:</label>
			<select id="account-type" name="account-type" >
				<option value="customer">Customer</option>		
			</select><br>
			<input type="submit" value="Sign Up">
		</form>
		<%
      	List errList = (List) request.getAttribute("errlist");      		
      	if(errList != null)
      	{
      		for(Iterator it = errList.iterator(); it.hasNext();)
      		{
      			String error = (String) it.next();
      			%>
	 			<font color ="red">
	 			<li> <%=error%> </li>
	 			</font>
	 			<%
      		}
     	}
   		%>
	</div>
      
</body>

	<br>
	<br>
	<br>
	<br>
	<br>
	<footer>
		<div class ="foot-link">
			<a href="ContactPage.jsp">Contact us</a> 
			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<a href="UserGuide.jsp">Help</a> 
		</div>
	
		<p>&copy; 2023 San Jose State University CS160-Team10. All Rights Reserved.</p>
	</footer>
</html>
