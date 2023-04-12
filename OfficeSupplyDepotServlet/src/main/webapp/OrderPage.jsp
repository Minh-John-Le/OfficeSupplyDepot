<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
<head>
	<title>View Orders</title>
	<link rel="stylesheet" href="OrderPage.css">
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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="OrderPage.jsp">Order</a> | <a href="AddProductPage.jsp">Inventory</a>
			</div>
		<%
			}
		%>
		
	</div>
	<!-- --------------------------------------------------------------------------------- -->
	<br>
	
	<div class="search-bar">
		<label for="order-number">Order Number:</label>
		<input type="text" id="order-number">
    <button type="button">Search</button>
	</div>
  <div>
  	<span> <b> Order Date </b></span>
    <label for="from-date">From:</label>
		<input type="date" id="from-date">
		<label for="to-date">To:</label>
		<input type="date" id="to-date">
  </div>
  <br>
   <div>
  	<span> <b> Expect Delivery Date </b></span>
    <label for="from-date">From:</label>
		<input type="date" id="from-date" value = "">
		<label for="to-date">To:</label>
		<input type="date" id="to-date">
  </div>
  
	<div class="order-cards">
		<div class="order-card">
			<div class="order-info">
				<h3>Order #12345</h3>
				<p>Ship To: John Doe, 123 Main St, Anytown, USA</p>
				<p>Total Weight: 5.2 lbs</p>
				<p>Total Price: $150.00</p>
				<p>Order Date: 2023-04-10</p>
				<p>Expected Delivery Date: 2023-04-15</p>
				<button>View Details</button>
				<button>Track Package</button>
			</div>
		</div>
		<div class="order-card">
			<div class="order-info">
				<h3>Order #67890</h3>
				<p>Ship To: Jane Smith, 456 Park Ave, Anytown, USA</p>
				<p>Total Weight: 2.8 lbs</p>
				<p>Total Price: $75.00</p>
				<p>Order Date: 2023-04-08</p>
				<p>Expected Delivery Date: 2023-04-13</p>
				<button>View Details</button>
				<button>Track Package</button>
			</div>
		</div>
	</div>
</body>
</html>
