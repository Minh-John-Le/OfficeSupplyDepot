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

<div  class="cart-page">

<div class="cart-container">
		<h1>Shopping Cart</h1>
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
						<p>Paper Clip
						
						Description: This is a book</p>
					</div>
				<td><input type="number" value="1" min="1">
				<button class="remove">Remove</button>
				</td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip
						
						Description: This is a book</p>
					</div>
				<td><input type="number" value="1" min="1">
				<button class="remove">Remove</button>
				</td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip
						
						Description: This is a book</p>
					</div>
				<td><input type="number" value="1" min="1">
				<button class="remove">Remove</button>
				</td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
			<tr>
				<td>
					<div class="item">
						<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
						<p>Paper Clip
						
						Description: This is a book</p>
					</div>
				<td><input type="number" value="1" min="1">
				<button class="remove">Remove</button>
				</td>
				<td>10.99</td>
				<td>10.99</td>
			</tr>
	
		</table>
	</div>
	<div class="checkout-container">
		<h1>Summary</h1>
		<table>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total items:</b> <br> 1000000 </td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total weight:</b> <br>100000 </td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Shipping</b> <br>TBD</td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Subtotal:</b> <br>$100.99</td>
			</tr>
		</table>
		<button class="checkout">Checkout</button>
	</div>
	</div>
	

</body>
</html>