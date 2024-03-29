<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Out</title>
<link rel="stylesheet" href="CheckoutPage.css">
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


<form action = "checkoutPage" method="post"> 
<div  class="cart-page">
	<%
		BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
		BigDecimal weight = (BigDecimal) session.getAttribute("weight");
		int totalItem = (int) session.getAttribute("totalItem");
		ShipMethod shipMethod  = (ShipMethod) session.getAttribute("shipMethod");	
		String shipMethodName = "";
		BigDecimal shipPrice = new BigDecimal(0);
		if (shipMethod != null)
		{
			shipMethodName = shipMethod.getName();
			shipPrice = shipMethod.getPrice();
		}
		PaymentAccount paymentAccount = (PaymentAccount) session.getAttribute("paymentAccount");
		String address = loginCustomer.getAddress();
		String accountName = "";
		String accountNumber = "";
		String expDate ="";
		
		if (paymentAccount != null)
		{
			accountName = paymentAccount.getName();
			accountNumber = paymentAccount.getCardNumber();
			expDate = paymentAccount.getExpireDate();
		}
		
	%>
	<div class="cart-container">
		<h1>Check out Information</h1>	
		<div class="bank-account">
			<h2>Shipping Information</h2>
			  <div class="form-row">
		        <label for="ship-name">Name:</label>
		        <input type="text" id="ship-name" name="ship-name" value="<%=displayName%>" required maxlength="20">
		      </div>
		      <div class="form-row">
		        <label for="ship-address">Shipping Address:</label>
		        <input type="text" id="ship-address" name="ship-address" value="<%=address%>" required maxlength="200">
		      </div>
		      <h2>Payment Information</h2>
		      <div class="form-row">
		        <label for="name">Name On Card:</label>
		        <input type="text" id="account-name" name="account-name" value="<%=accountName%>" required maxlength="50">
		      </div>
		      <div class="form-row">
		        <label for="account-number">Card Number:</label>
		        <input type="text" id="account-number" name="account-number" value="<%=accountNumber%>" required maxlength="20">
		      </div>
		      <div class="form-row">
		        <label for="exp">Expiration Date:</label>
		        <input type="text" id="exp" name="exp" value="<%=expDate%>" placeholder="MM/YY" required maxlength ="5">
		      </div>
		  </div>
		  
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
				<td><b>Ship Method:</b> <br> <%=shipMethodName%> - $<%=shipPrice%></td>
			</tr>
			<tr class="checkout-table">
				<td></td>
				<td><b>Total:</b> <br>$<%=totalPrice%></td>
			</tr>
		</table>
		
		<button class="checkout" name = "checkout" value = "checkout">Check Out</button>
		
		<%
      	List errList = (List) request.getAttribute("errlist");      		
      	if(errList != null)
      	{
      		for(Iterator it = errList.iterator(); it.hasNext();)
      		{
      			String error = (String) it.next();
      			%>
	 			<font color ="red">
	 			<li> <%=error%> </li>
	 			</font>
	 			<%
      		}
     	}
   		%>
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