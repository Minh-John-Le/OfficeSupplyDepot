<%@page import = "Beans.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Office Supply Depot</title>
	<link rel="stylesheet" href="MainPage.css">
</head>
<body>
	<div class="header">
		<div class="logo">
			<img src="my-logo.png" alt="Office Supply Depot &nbsp &nbsp">
		</div>
		<div class="search">
			<input type="text" placeholder="Search for items...">
			<button>Search</button>
		</div>
		<%
			Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
			Store loginStore = (Store) session.getAttribute("loginStore");
			if (loginCustomer == null && loginStore == null)
			{
		%>
			<div class="login-signup">
				<a href="Login.jsp">Login</a> | <a href="SignUp.jsp">Sign up</a>
			</div>
		<%
			}
			else if (loginCustomer != null)
			{
		%>
			<div class="login-signup">
				<a href="#">Account</a> | <a href="#">Order</a>| <a href="#">Cart</a>
			</div>
		<%
			}
		%>
		
	</div>
	<div class="item-container">
		<div class="item-box">
			<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
		<div class="item-box">
			<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
		<div class="item-box">
			<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
		<div class="item-box">
			<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
	</div>
</body>
</html>
