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
			<p>Welcome to the Office Supply Depot! This is the project of students in team 10 from San Jose State University.</p>
			
			<p>
			Office Supply Depot is an online office retailer with 2 warehouses in San Jose. The warehouse doesn't have a
			showroom as all the items for sale are listed online. It has a pickup area for customer to pick up their prepaid orders made
			online. It offers free delivery services for any orders over $100.00. For any orders that are less than 15lbs, the delivery
			will be done by a drone on the same day during business hours. Otherwise the orders will be delivered by delivery truck
			within 2 business days. For any order that are under $100, customer can request deliveries (drone or truck) by paying a
			surcharge of $20. For same day truck delivery of orders over $100, customer can pay a surcharge of $25.
			</p>
			<p>
			Our team is responsible to develop the IT infrastructure and website for customers and store administrators. Customer will be
			able to buy various office supply items by first placing them into a shopping cart before they check-out. The store
			manager should be able to update the inventory using an administrator panel when new stock are being delivered to the
			warehouse from the suppliers. Each truck will need to be able to optimize their delivery routing based on customer
			locations.
			</p>
			
			<p>The next part of this guide,"How to use", will help you get started with our website and provide you with all the information you need to use it.</p>
			
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
			
			<h3>Account Page</h3>
			<ul>
				<li><b>Username and email:</b> those two field is used for recovery and contacting method, so they cannot be change.</li>
				<li><b>Update Account:</b> Directly write your new information in the account form's textbox and click "Update" button.</li>
				<li><b>Logout:</b> Click "log out" button.</li>
			</ul>
			
			<h3>Order Page</h3>
			<ul>
				<li><b>Search Order:</b> Simply click "search" button. You can apply input appropriate filter to serach for a specific order</li>
				<li><b>Track Package</b> After searching for order, the list of order will appear. Click on track package to see the map and route that Office Supply Depot use to deliver package to shipping address</li>
				<li><b>View Order Details:</b> After searching for order, the list of order will appear. Click on "View Details" button to see the details.</li>
			</ul>
			
			<h3>Cart Page</h3>
			<ul>
				<li><b>Purchase Items</b> After place item to cart, navigate to cart page and follow instruction on the page to check out.</li>
			</ul>
			
		</section>
		<section id="section3">
			<h2>FAQs</h2>
			<dl>
				<dt><b>Question 1:</b> How do I reset my password?</dt>
				<dd>Answer: At the current version of Office Supply Depot, dev team has not implement password reseting.</dd>
				<dt><b>Question 2:</b> How do I contact customer support?</dt>
				<dd>Answer: You can contact customer support by phone or email. Our contact information can be found on the Contact Us page.</dd>
				<dt><b>Question 3:</b> How do I update my account information?</dt>
				<dd>Answer: To update your account information, simply log in to your account and click on your display name on the header.</dd>
			</dl>
		</section>
	</main>
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
</body>
</html>
