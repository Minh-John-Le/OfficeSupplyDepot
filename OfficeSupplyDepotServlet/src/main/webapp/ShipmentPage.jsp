<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>
<%@page import = "java.time.LocalDate" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shipping Method</title>
<link rel="stylesheet" href="ShipmentPage.css">
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


<form action = "shipmentPage" method="post"> 
<div  class="cart-page">
	<%
		BigDecimal subtotal = (BigDecimal) session.getAttribute("subtotal");
		BigDecimal weight = (BigDecimal) session.getAttribute("weight");
		int totalItem = (int) session.getAttribute("totalItem");
		LinkedList<ShipMethod> availableShipMethodList = (LinkedList<ShipMethod>) session.getAttribute("availableShipMethodList");
	%>
	<div class="cart-container">
	<h1>Shipping Method</h1>	
	<% if(availableShipMethodList != null) {
			for (int i = 0; i < availableShipMethodList.size(); i++)
			{
				String name = availableShipMethodList.get(i).getName();
				BigDecimal price = availableShipMethodList.get(i).getPrice();
				int speed = availableShipMethodList.get(i).getSpeed();
				int Id = availableShipMethodList.get(i).getId();
				String speedStr = String.valueOf(speed);
				LocalDate deliveryDay = LocalDate.now().plusDays(speed);
				String deliveryDayStr = deliveryDay.toString();
				
				
	%>
		  <div class="card">
		  <div class="card-header">
		    <input type="checkbox" class="checkbox-class" name="myCheckbox" value =<%=Id%>>
		    <label for="checkbox1"></label>
		    <h3><%=name%></h3>
		  </div>
		  <div class="card-body">
		    <p>Price: $<%=price%></p>
		    <p>Speed: <%=speed%> Day(s)</p>
		    <p>Expect Delivery Date: <%=deliveryDayStr%></p>
		  </div>
		  </div>	
	<%
			}
		} 
	%>
	
	</div>
	<div class="checkout-container">
		<h1>Summary</h1>
		<table>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total items:</b> <br> <%=totalItem%> </td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total weight:</b> <br><%=weight%> lbs </td>
			</tr>
			<tr>
				<td></td>
				<td><b>Ship Method:</b> <br> TBD</td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Subtotal:</b> <br>$<%=subtotal%></td>
			</tr>
		</table>
		
		<button class="checkout" name = "next" value ="next" >Next</button>
	</div>
	
</div>
</form>
	<script src = "ShipmentPage.js"></script>
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