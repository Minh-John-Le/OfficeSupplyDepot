<%@page import="Beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link rel="stylesheet" href="CartPage.css">
</head>
<body>
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<div class="search">
			<input type="text" placeholder="Search for items...">
			<button>Search</button>
		</div>
		<%
			Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
			Store loginStore = (Store) session.getAttribute("loginStore");
			String displayName = "";
			if (loginCustomer == null && loginStore == null)
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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="#">Order</a> | <a href="CartPage.jsp">Cart</a>
			</div>
		<%
			}
			else if (loginStore != null)
			{
				displayName = loginStore.getStoreName();
		%>
		
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="#">Order</a> | <a href="#">Inventory</a>
			</div>
		<%
			}
		%>
	</div>

	<h1>Shopping Cart</h1>
	<div class="cart-table">
		<table>
			<tr>
				<th>Product</th>
				<th>Quantity</th>
				<th>Price</th>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				</td>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				</td>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
			</tr>
		</table>
		<p>Subtotal: 32.97</p>
		<button class="checkout">Checkout</button>
	</div>

</body>
</html>