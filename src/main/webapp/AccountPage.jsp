<%@page import="beans.*" %>
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
			
			// information
			String username = "";
			String email = "";
			String password = "";
			String displayName = "";
			String address = "";
			
			// account
			String accountName = "";
			String accountNumber = "";
			String expDate = "";
			if (loginCustomer == null && loginAdmin == null)
			{
				%>
				<script type="text/javascript">
					window.location.href = "MainPage.jsp";
				</script>
					<p>If you are not redirected automatically, please click <a href="MainPage.jsp">here</a></p>
				<%
			}
			if (loginCustomer != null)
			{
				username = loginCustomer.getUsername();
				email = loginCustomer.getEmail();
				password = loginCustomer.getPassword();
				displayName = loginCustomer.getCustomerName();
				address = loginCustomer.getAddress();
			}
			else if (loginAdmin != null)
			{
				username = loginAdmin.getUsername();
				email = loginAdmin.getEmail();
				password = loginAdmin.getPassword();
				displayName = loginAdmin.getAdminName();
			}
		
			if (paymentAccount != null)
			{
				accountName = paymentAccount.getName();
				accountNumber = paymentAccount.getCardNumber();
				expDate = paymentAccount.getExpireDate();
			}
	%>
	<form action = "account" method="post"> 
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<%
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
	      <div class="form-row">
	        <label for="display-name">Display Name:</label>
	        <input type="text" id="display-name" name="display-name" value="<%=displayName%>">
	      </div>
	      <div class="form-row">
	        <label for="password">Password:</label>
	        <input type="password" id="password" name="password" value="<%=password%>">
	      </div>
	      
	      <% if (loginCustomer != null) {%>
	      <div class="form-row">
	        <label for="address">Address:</label>
	        <textarea id="address" name="address"><%=address%></textarea>
	      </div>
	      <%} %>
	    </div>
	    <!----------------------------------------->
	    <% if (loginCustomer != null) {%>
	    <div class="bank-account">
	      <h2>Bank Account Information</h2>
	      <div class="form-row">
	        <label for="name">Name on Account:</label>
	        <input type="text" id="account-name" name="account-name" value="<%=accountName%>">
	      </div>
	      <div class="form-row">
	        <label for="account-number">Account Number:</label>
	        <input type="text" id="account-number" name="account-number" value="<%=accountNumber%>">
	      </div>
	      <div class="form-row">
	        <label for="exp">Expiration Date:</label>
	        <input type="text" id="exp" name="exp" value="<%=expDate%>" placeholder="MM/YY">
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
		<div class="home-link">
			<a href="ContactPage.jsp">Contact us</a> 
			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<a href="UserGuide.jsp">Help</a> 
		</div>
	
		<p>&copy; 2023 San Jose State University CS160-Team10. All Rights Reserved.</p>
	</footer>
</html>
