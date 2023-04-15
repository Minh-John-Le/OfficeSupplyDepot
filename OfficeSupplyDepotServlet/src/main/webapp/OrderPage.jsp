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
	<!-- --------------------------------------------------------------------------------- -->
	<br>
	<%
		OrderPageFilter orderPageFilter = (OrderPageFilter) session.getAttribute("orderPageFilter");
		LinkedList<OrderDetail> orderDetailList = (LinkedList<OrderDetail>) session.getAttribute("orderDetailList");
		String orderNumber = "";
		String fromOrderDay = "";
		String toOrderDay = "";
		String fromDeliveryDay ="";
		String toDeliveryDay = "";
		String sortBy = "";
		
		if (orderPageFilter != null)
		{
			orderNumber = orderPageFilter.getOrderNumber();
			fromOrderDay = orderPageFilter.getFromOrderDay();
			toOrderDay = orderPageFilter.getToOrderDay();
			fromDeliveryDay = orderPageFilter.getFromDeliveryDay();
			toDeliveryDay = orderPageFilter.getToDeliveryDay();
			sortBy = orderPageFilter.getSortBy();
		}
		
		
	%>
<form action = "orderPage" method="post">
	
	<div class="search-bar">
		<label for="order-number"><b>Order Number:</b></label>
		<input type="text" name = "order-number" value ="<%=orderNumber%>">
    	<button type="submit" name = "search-button" value ="search-button">Search</button>
	</div>
  <div>
  	<span> <b> Order Date -</b></span>
    <label for="from-date">From:</label>
	<input type="date" id="from-order-date" name = "from-order-date" value = "<%=fromOrderDay%>">
	<label for="to-date">To:</label>
	<input type="date" id="to-order-date" name = "to-order-date" value = "<%=toOrderDay%>">
  </div>
  <br>
  <div>
  	<span> <b> Expect Delivery Date -</b></span>
    <label for="from-date">From:</label>
	<input type="date" id="from-delivery-date" name = "from-delivery-date" value = "<%=fromDeliveryDay%>">
	<label for="to-date">To:</label>
	<input type="date" id="to-delivery-date" name = "to-delivery-date" value = "<%=toDeliveryDay%>">
  </div>
  <br>
  <div>
 	 <span> <b> Sort by </b></span>
	  <select id="sortBy" name="sortBy" required>
	     <option value="Order_Date DESC" <%= sortBy.equals("Order_Date DESC") ? "selected" : "" %>>Newest Order</option>
		 <option value="Order_Date ASC" <%= sortBy.equals("Order_Date ASC") ? "selected" : "" %>>Oldest Order</option>
	     <option value="Delivery_Date DESC" <%= sortBy.equals("Delivery_Date DESC") ? "selected" : "" %>>Newest Delivery Date</option>
 		 <option value="Delivery_Date ASC" <%= sortBy.equals("Delivery_Date ASC") ? "selected" : "" %>>Oldest Delivery Date</option>
	 </select>
  </div>
  <hr>
  <br>
   <!-- ---------------------------------------------------------------------------------- -->
	<div class="order-cards">
		<%
		if (orderDetailList != null && orderDetailList.size() > 0)
		{
			for (OrderDetail orderDetail: orderDetailList)
			{
			
		%>
		<div class="order-card">
			<div class="order-info">
				<h3><b>Order #</b> <%=orderDetail.getOrderCode()%></h3>
				<p><b>Ship To:</b> <%=orderDetail.getDeliveryName()%>, <%=orderDetail.getShipAddress() %></p>
				<p><b>Total Weight:</b> <%=orderDetail.getTotalWeight() %> lbs</p>
				<p><b>Total Price:</b> $<%=orderDetail.getTotalPrice()%></p>
				<p><b>Order Date:</b> <%=orderDetail.getOrderDate()%></p>
				<p><b>Expected Delivery Date:</b> <%=orderDetail.getDeliveryDate()%></p>
				<button type="submit" name = "view details" value = "<%=orderDetail.getId()%>">View Details</button>
				<button type="submit" name = "track Package" value = "<%=orderDetail.getShipAddress()%>">Track Package</button>
			</div>
		</div>
		<%
			}
		}
		
		%>

	</div>
	<%
		if (orderDetailList == null || orderDetailList.size() <= 0)
		{
			%>
			  	
			
			<div class="card">
			  <div class="card-body">
			    <h5 class="card-title">Hello, again</h5>
			    <p class="card-text">To view your order, simply click the search button.</p>
			    <p class="card-text"><b>Note:</b></p>
			    <ul>
			     
			      <li>By default, Office Supply Depot will shows Order within 7 days, and the order delivery range is two weeks counting from last week.</li>
					<li>You can change the filter such as sort by, and input order number to search for a specific order.</li>
				
			    </ul>
			  </div>
			</div>				
			<%
		}
		%>
	
	</form>
</body>

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
</html>
