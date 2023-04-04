<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>

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
		<div class="form-group">
            <select id="category" name="category" required>
            	<option value="All">All</option>
                <option value="Electronics">Electronics</option>
                <option value="Craft Supplies">Craft Supplies</option>
                <option value="Office Furniture">Office Furniture</option>
                <option value="Essentials">Essentials</option>
            </select>
        </div>
		<div class="search">
			<input type="text" placeholder="Search for items..." name = "search text">
			<button name = "button" value = "search">Search</button>
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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="upload.html">Order</a> | <a href="CartPage.jsp#">Cart</a>
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
			<%
			LinkedList<CartItem> cartItemList = (LinkedList<CartItem>) session.getAttribute("cartItemList");
					
			if(cartItemList != null)
      		{				
      			for(int i = 0 ; i < cartItemList.size(); i++)
      			{
      			String imageUrl = cartItemList.get(i).getProduct().getImageURL();
      			String productname = cartItemList.get(i).getProduct().getName();
      			int quantity= cartItemList.get(i).getQuantity();
      			BigDecimal weight = cartItemList.get(i).getProduct().getWeight();
      			BigDecimal price = cartItemList.get(i).getProduct().getPrice();
      			String description = cartItemList.get(i).getProduct().getDescription();
      			int productId = cartItemList.get(i).getProduct().getId();
      			%>	
      			
			<tr>
				<td>
					<div class="item">
						<img src="<%=imageUrl%>">
						<p><%=productname%>
						
						Description: <%=description%></p>
					</div>
				<td><input type="number" value="<%=quantity%>" min="1">
				<button class="remove">Remove</button>
				</td>
				<td><%=weight%></td>
				<td><%=price%></td>
			</tr>
        <% }
         }%>
         	
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