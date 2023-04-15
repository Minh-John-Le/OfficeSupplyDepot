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
			<h3>Header</h3>
			<ul>
				<li><b>Navigate to Main Page:</b> click our website name "Office Supply Depot".</li>
				<li><b>Navigate to Sign up Page:</b> click "Sign up".</li>
				<li><b>Navigate to Login Page:</b> click "Login".</li>
				<li><b>Navigate to Account Page:</b> After login, customer display name will appear on header. Click that name to navigate to Account Page.</li>
				<li><b>Navigate to Order Page:</b> After login, "Order" will appear on header. Click it to get to Order Page.</li>
				<li><b>Navigate to Cart Page:</b> After login, "Cart" will appear on header. Click it to get to Order Page.</li>
			</ul>

			<h3>Footer</h3>
			<ul>
				<li><b>Contact dev team:</b> click "Contact" to see dev team information.</li>
				<li><b>Get full user guide:</b> click "Help" to navigate to user guide page.</li>
			</ul>

			<h3>Main Page</h3>
			<ul>
				<li><b>Search Item:</b> simply click "search" to view all items in the store or include filter to search for specific item.</li>
				<li><b>Category Filter:</b> the first drop down box next to "Office Supply Depot" on header is category filter. Click it and choose the desired category to filter product.</li>
				<li><b>Sort Filter:</b> the next drop down box, which is before search's texbox is sort. This will indicate how items will be organize after the search.</li>
				<li><b>Search's Textbox:</b> Search's textbox have auto complete search(e.g: input "note" will give user both "note" and "notebook"). Enter desired item name in this box.</li>
				<li><b>Add item to cart:</b> after search, item will appear with "add to cart" button. Click those button will add item to cart.</li>
			</ul>

			<h3>Account Page</h3>
			<ul>
				<li><b>Username and email:</b> those two field is used for recovery and contacting method, so they cannot be change.</li>
				<li><b>Update Account:</b> Directly write your new information in the account form's textbox and click "Update" button.</li>
				<li><b>Logout:</b> Click "log out" button.</li>
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
