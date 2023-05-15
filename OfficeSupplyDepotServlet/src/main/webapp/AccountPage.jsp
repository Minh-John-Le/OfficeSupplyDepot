<%@page import = "Beans.*" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Account Information</title>
  <link rel="stylesheet" href="AccountPage.css">
</head>
<body>
	<%
			Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
			OSDAdmin loginAdmin = (OSDAdmin) session.getAttribute("loginAdmin");
			PaymentAccount paymentAccount = (PaymentAccount) session.getAttribute("paymentAccount");
			
			// customer information
			String username = "";
			String email = "";
			String password = "";
			String displayName = "";
			String address = "";
			
			// credit card info
			String creditCardName = "";
			String creditCardNumber = "";
			String creditCardExpDate = "";
			if (loginCustomer == null && loginAdmin == null)
			{
				%>
				<script type="text/javascript">
					window.location.href = "MainPage.jsp";
				</script>
					<p>If you are not redirected automatically, please click <a href="MainPage.jsp">here</a></p>
				<%
			}
			/* Customer logged in  */
			if (loginCustomer != null)
			{
				username = loginCustomer.getUsername();
				email = loginCustomer.getEmail();
				password = loginCustomer.getPassword();
				displayName = loginCustomer.getCustomerName();
				address = loginCustomer.getAddress();
			}
			/* Admin logged in  */
			else if (loginAdmin != null)
			{
				username = loginAdmin.getUsername();
				email = loginAdmin.getEmail();
				password = loginAdmin.getPassword();
				displayName = loginAdmin.getAdminName();
			}
			/* Payment Account Exists  */
			if (paymentAccount != null)
			{
				creditCardName = paymentAccount.getName();
				creditCardNumber = paymentAccount.getCardNumber();
				creditCardExpDate = paymentAccount.getExpireDate();
			}
	%>
	<form action = "account" method="post"> 
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<%
		/* User not logged in, show Login or SignUp redirect link button  */
			if (loginCustomer == null && loginAdmin == null)
			{
		%>
			<div class="info-section">
				<a href="Login.jsp">Login</a> | <a href="SignUp.jsp">Sign up</a>
			</div>
		<%
			}
		/* Customer logged in.  */
			else if (loginCustomer != null)
			{
	
				displayName = loginCustomer.getCustomerName();
		%>
		<!-- Show the user's display-name and provide a link to orders and cart.  -->
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="OrderPage.jsp">Order</a> | <a href="CartPage.jsp#">Cart</a>
			</div>
		<%
			}
		/*  Admin account */
			else if (loginAdmin != null)
			{
				/* Set display-name to admin name.  */
				displayName = loginAdmin.getAdminName();
		%>
			<!-- Show Admin Orders and Inventory page.  -->
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="OrderPage.jsp">Order</a> | <a href="InventoryPage.jsp">Inventory</a>
			</div>
		<%
			}
		%>
		
	</div>
<!-- --------------------------------- -->
	  <div class="container" >
	    <div class="account-info">
	      <h2>Account Information</h2>
	      <div class="form-row">
	        <label for="username">Username:</label>
	        <input type="text" id="username" name="username" readonly value= <%=username%>>
	      </div>
	      <div class="form-row">
	        <label for="email">Email:</label>
	        <input type="email" id="email" name="email" readonly value=<%=email%>>
	      </div>
	      <br>
	      <hr>
	      <br>
	      <!-- Changeable  -->
	      <div class="form-row">
	        <label for="display-name">Display Name:</label>
	        <input type="text" id="display-name" name="display-name" value="<%=displayName%>" maxlength="20">
	      </div>
	      <!-- Changeable  -->
	      <div class="form-row">
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" value="<%=password%>" maxlength="20">
	      </div>
	      <!-- Changeable  -->
	      <% if (loginCustomer != null) {%>
	      <div class="form-row">
	        <label for="address">Address:</label>
	        <textarea id="address" name="address"><%=address%></textarea>
	      </div>
	      <%} %>
	    </div>
	    <!----------------------------------------->
	    <!-- Customer logged in show credit card elements.  -->
	    <% if (loginCustomer != null) {%>
	    <div class="credit-card">
	      <h2>Credit Card Information</h2>
	      <div class="form-row">
	        <label for="name">Name on Credit Card:</label>
	        <input type="text" id="account-name" name="account-name" value="<%=creditCardName%>" maxlength="255">
	      </div>
	      <div class="form-row">
	        <label for="account-number">Credit Card Number:</label>
	        <input type="text" id="account-number" name="account-number" value="<%=creditCardNumber%>" maxlength="19">
	      </div>
	      <div class="form-row">
	        <label for="exp">Expiration Date:</label>
	        <input type="text" id="exp" name="exp" value="<%=creditCardExpDate%>" placeholder="MM/YY" maxlength="5">
	      </div>
	    </div>
	    <%} %>
	    <div class="buttons">
	      <button type = "submit" class="update-btn" value="update-btn" name = "button" >Update</button>
	      <button type = "submit" class="logout-btn" value="logout-btn" name = "button">Log Out</button>
	    </div>
	    
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
