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

		<%
			Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
			OSDAdmin loginAdmin = (OSDAdmin) session.getAttribute("loginAdmin");
			String displayName = "";
			if (loginCustomer == null && loginAdmin == null)
			{
				%>
				<script type="text/javascript">
					window.location.href = "MainPage.jsp";
				</script>
					<p>If you are not redirected automatically, please click <a href="MainPage.jsp">here</a></p>
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


<form action = "cartpage" method="post"> 
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
      			String barcode = cartItemList.get(i).getProduct().getBarcode();
      			int stock = cartItemList.get(i).getProduct().getStock();
      			%>	
      			
			<tr>
				<td>
					<div class="item">
						<img src="<%=imageUrl%>">
						<span><b><%=productname%></b>
						<b>Stock:</b> <%=stock%>
						<b>Description:</b> <%=description%></span>
					</div>
				<td><input type="number" name="quantity_<%=barcode%>" value="<%=quantity%>" min="1" max = "10">
				<button class="remove" value = "<%=barcode%>" name = "remove">Remove</button>
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
				<td><b>Total items:</b> <br> TBD</td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total weight:</b> <br> TBD</td>
			</tr>
			<tr>
				<td></td>
				<td><b>Ship Method:</b> <br> TBD</td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Subtotal:</b> <br> TBD</td>
			</tr>
		</table>
		<% 
		if (cartItemList == null || cartItemList.size() == 0) {
		}
		else
		{
		%>
		<button class="checkout" name ="next" value = "next">Next</button>
		<%
		}
		%>
	</div>
	
</div>

</form>

</body>
</html>