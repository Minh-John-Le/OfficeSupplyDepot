<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
<head>
	<title>User Guide</title>
	<link rel="stylesheet" type="text/css" href="UserGuide.css">
</head>
<body>
	<div class="header">
	  		<div class="home-link">
	  			<a href="MainPage.jsp"> Office Supply Depot </a>
	  		</div>

	  		<%
			Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
			OSDAdmin loginAdmin = (OSDAdmin) session.getAttribute("loginAdmin");
			String displayName = "";
			if (loginCustomer == null && loginAdmin == null)
			{
		%>
			<div class="info-section">
				<a href="Login.jsp">Login</a> | <a href="SignUp.jsp">Sign up</a>
			</div>
		<%
			}
			else if (loginCustomer != null)
			{
	
				displayName = loginCustomer.getCustomerName();
		%>
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="OrderPage.jsp">Order</a> | <a href="CartPage.jsp#">Cart</a>
			</div>
		<%
			}
			else if (loginAdmin != null)
			{
				displayName = loginAdmin.getAdminName();
		%>
		
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="OrderPage.jsp">Order</a> | <a href="InventoryPage.jsp">Inventory</a>
			</div>
		<%
			}
		%>
	  	</div>
	  	<br>

	
	</div class = "userguide-header">
	<h1>User Guide</h1>
	<nav>
		<ul>
			<li><a href="#section1">Getting Started</a></li>
			<li><a href="#section2">How to Use</a></li>

			<li><a href="#section3">FAQs</a></li>
		</ul>
	</nav>
	</div>

	<main>
		<section id="section1">
			<h2>Getting Started</h2>
			<p>Welcome to the Office Supply Depot! This guide will help you get started with our website and provide you with all the information you need to use it.</p>
			<p>To get started, simply follow the steps below:</p>
			<ol>
				<li>Step 1: Click on Sign up to get a new account or login if you already have one</li>
				<li>Step 2: Enter your login credentials.</li>
				<li>Step 3: Then Navigate to Mainpage and Click Search to see al product</li>
			</ol>
		</section>
		<section id="section2">
			<h2>How to Use</h2>

			<p><b>Main Page:</b> </p>
			<ul>
				<li>Search Item: simply click search to view all items in the store or include filter to search for specific item</li>
				<li>Order: click on order next to your display name to navigate to order page</li>
				<li> Dev team info: click "Contact" to see dev team information</li>
				<li>Account Information: After login click on your display name to navigate to your account page</li>
				<li>Update Account: Directly write your new information in the account form and click "Update" button</li>
				<li>Logout: Click "log out" button</li>
			</ul>

		</section>
		<section id="section3">
			<h2>FAQs</h2>
			<dl>
				<dt>Question 1: How do I reset my password?</dt>
				<dd>Answer: To reset your password, simply click on the "Forgot Password" link and follow the instructions.</dd>
				<dt>Question 2: How do I contact customer support?</dt>
				<dd>Answer: You can contact customer support by phone or email. Our contact information can be found on the Contact Us page.</dd>
				<dt>Question 3: How do I update my account information?</dt>
				<dd>Answer: To update your account information, simply log in to your account and click on the "Settings" link.</dd>
			</dl>
		</section>
	</main>
	<br>
	<br>
	<br>
	<br>
	<br>
	<footer>
		<div class="home-link">
			<a href="ContactPage.jsp">Contact us</a> 
			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<a href="UserGuide.jsp">Help</a> 
		</div>
	
		<p>&copy; 2023 San Jose State University CS160-Team10. All Rights Reserved.</p>
	</footer>
</body>
</html>
