<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Details</title>
<link rel="stylesheet" href="OrderDetailPage.css">
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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="OrderPage.jsp">Order</a> | <a href="InventoryPage.jsp">Inventory</a>
			</div>
		<%
			}
		%>
		
	</div>


<form action = "cartpage" method="post"> 
<div  class="cart-page">
	<% 
	ShipMethod viewedshipMethod = (ShipMethod) session.getAttribute("viewedShipMethod");
	OrderDetail orderDetail = (OrderDetail) session.getAttribute("viewedOrderDetail");
	
	String shipMethodName = "";
	BigDecimal shipCost = new BigDecimal(0);
	
	if (viewedshipMethod != null)
	{
		shipMethodName = viewedshipMethod.getName();
		shipCost = viewedshipMethod.getPrice();
	}
	
	
	BigDecimal totalPrice = new BigDecimal(0);
	BigDecimal totalWeight = new BigDecimal(0);
	int totalItem = 0;
	String shipName = "";
	String address = "";
	String accountName = "";
	int accountNumber = 0;
	String expDate = "";

	
	if (orderDetail != null)
	{
		totalPrice = orderDetail.getTotalPrice();
		totalWeight = orderDetail.getTotalWeight();
		totalItem = orderDetail.getTotalItem();
		shipName = orderDetail.getDeliveryName();
		address = orderDetail.getShipAddress();
		accountName = orderDetail.getCardName();
		accountNumber = orderDetail.getPaymentCardNumber();
		expDate = orderDetail.getExpireDate();
		
	}
	
	
	
	%>

	<div class="cart-container">
		<h1>Order Items</h1>
		
		<table>
			<tr>
				<th>Product</th>
				<th>Quantity</th>
				<th>Weight</th>
				<th>Price</th>
			</tr>
			<%
			LinkedList<CartItem> packageItemList = (LinkedList<CartItem>) session.getAttribute("packageItemList");
					
			if(packageItemList != null && packageItemList.size() > 0)
      		{				
      			for(int i = 0 ; i < packageItemList.size(); i++)
      			{
      			String imageUrl = packageItemList.get(i).getProduct().getImageURL();
      			String productname = packageItemList.get(i).getProduct().getName();
      			int quantity= packageItemList.get(i).getQuantity();
      			BigDecimal weight = packageItemList.get(i).getProduct().getWeight();
      			BigDecimal price = packageItemList.get(i).getProduct().getPrice();
      			String description = packageItemList.get(i).getProduct().getDescription();
      			int productId = packageItemList.get(i).getProduct().getId();
      			String barcode = packageItemList.get(i).getProduct().getBarcode();
      			int stock = packageItemList.get(i).getProduct().getStock();
      			%>	
      			
			<tr>
				<td>
					<div class="item">
						<img src="<%=imageUrl%>">
						<span><b><%=productname%></b>
						<b>Stock:</b> <%=stock%>
						<b>Description:</b> <%=description%></span>
					</div>
				<td><input type="number" name="quantity_<%=barcode%>" value="<%=quantity%>" readonly>
				</td>
				<td><%=weight%></td>
				<td><%=price%></td>
			</tr>
        <% 
        		}
         }
         %>
         	
		</table>
		
		<br>
		<br>
		<!-- ------------------------------------------------ -->
		<h1>Check out Information</h1>	
		<div class="bank-account">
			<h2>Shipping Information</h2>
			  <div class="form-row">
		        <label for="ship-name">Name:</label>
		        <input type="text" id="ship-name" name="ship-name" value="<%=shipName%>" readonly>
		      </div>
		      <div class="form-row">
		        <label for="ship-address">Shipping Address:</label>
		        <input type="text" id="ship-address" name="ship-address" value="<%=address%>" readonly>
		      </div>
		      <h2>Payment Information</h2>
		      <div class="form-row">
		        <label for="name">Name On Card:</label>
		        <input type="text" id="account-name" name="account-name" value="<%=accountName%>" readonly>
		      </div>
		      <div class="form-row">
		        <label for="account-number">Card Number:</label>
		        <input type="text" id="account-number" name="account-number" value="<%=accountNumber%>" readonly>
		      </div>
		      <div class="form-row">
		        <label for="exp">Expiration Date:</label>
		        <input type="text" id="exp" name="exp" value="<%=expDate%>" placeholder="MM/YY" readonly>
		      </div>
		  </div>
		
		
	</div>
	<div class="checkout-container">
		<h1>Summary</h1>
		<table>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total items:</b> <br> <%=totalItem %></td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total weight:</b> <br> <%=totalWeight %> lbs</td>
			</tr>
			<tr>
				<td></td>
				<td><b>Ship Method:</b> <br> <%=shipMethodName%> - $<%=shipCost %></td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total:</b> <br> $<%=totalPrice %></td>
			</tr>
		</table>
	</div>
	
</div>

</form>

</body>

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
</html>