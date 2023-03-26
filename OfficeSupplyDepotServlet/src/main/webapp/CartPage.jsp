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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="upload.html">Order</a> | <a href="#">Cart</a>
			</div>
		<%
			}
			else if (loginAdmin != null)
			{
				displayName = loginAdmin.getAdminName();
		%>
		
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="#">Order</a> | <a href="AddProductPage.jsp">Inventory</a>
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
				<th>Weight</th>
				<th>Price</th>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip</p>
					</div>
				<td><input type="number" value="1" min="0"></td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			
			<tr>
				<td></td>
				<td><b>Total items:</b> <br> 1000000 </td>
				<td><b>Total weight:</b> <br>100000 </td>
				<td><b>Subtotals:</b> <br>$100.99</td>
			</tr>
	
		</table>
		<p></p>
		<button class="checkout">Checkout</button>
			
	</div>
	

</body>
</html>