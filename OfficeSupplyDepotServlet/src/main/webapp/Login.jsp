<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>

<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
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
			<input type="text" id="username" name="username" placeholder="Enter your username">
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" placeholder="Enter your password">
			<label for="type">Login as:</label>
			<select id="account-type" name="account-type">
				<option value="customer">Customer</option>
				<option value="store">Store</option>
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
</html>