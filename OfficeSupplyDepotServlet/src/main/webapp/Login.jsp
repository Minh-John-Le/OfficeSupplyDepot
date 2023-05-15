<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="Login.css">
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
	<div class="login-container">
		<h1>Login</h1>
		<form action="login" method="post">
			<label for="username">Username:</label>
			<input type="text" id="username" name="username" placeholder="Enter your username" required maxlength="50">
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" placeholder="Enter your password" required maxlength="50">
			<label for="type">Login as:</label>
			<select id="account-type" name="account-type">
				<option value="customer">Customer</option>
				<option value="admin">Admin</option>
			</select>
			<input type="submit" value="Login">
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
