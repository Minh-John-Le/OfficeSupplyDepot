<%@page import = "Beans.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Account Page</title>
  <link rel="stylesheet" href="AccountPage.css">
</head>
<body>
	<%
		Customer loginCustomer = (Customer) session.getAttribute("loginCustomer");
		Store loginStore = (Store) session.getAttribute("loginStore");
		PaymentAccount paymentAccount = (PaymentAccount) session.getAttribute("paymentAccount");
		BankAccount bankAccount = (BankAccount) session.getAttribute("bankAccount");
		
		// information
		String username = "";
		String email = "";
		String password = "";
		String displayName = "";
		String address = "";
		
		// account
		String accountName = "";
		int accountNumber = -1;
		String expDate = "";
		
		if (loginCustomer != null)
		{
			username = loginCustomer.getUsername();
			email = loginCustomer.getEmail();
			password = loginCustomer.getPassword();
			displayName = loginCustomer.getCustomerName();
			address = loginCustomer.getAddress();
		}
		else if (loginStore != null)
		{
			username = loginStore.getUsername();
			email = loginStore.getEmail();
			password = loginStore.getPassword();
			displayName = loginStore.getStoreName();
			address = loginStore.getAddress();
		}
	
		if (paymentAccount != null)
		{
			accountName = paymentAccount.getName();
			accountNumber = paymentAccount.getCardNumber();
			expDate = paymentAccount.getExpireDate();
		}
		else if (bankAccount != null)
		{
			accountName = bankAccount.getName();
			accountNumber = bankAccount.getBankAccountNumber();
			expDate = bankAccount.getExpireDate();
		}
	%>
	<form action = "account" method="post"> 
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<%
			if (loginCustomer == null && loginStore == null)
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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="#">Order</a> | <a href="#">Cart</a>
			</div>
		<%
			}
			else if (loginStore != null)
			{
				displayName = loginStore.getStoreName();
		%>
		
			<div class="info-section">
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="#">Order</a> | <a href="#">Inventory</a>
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
	      <div class="form-row">
	        <label for="address">Address:</label>
	        <textarea id="address" name="address"><%=address%></textarea>
	      </div>
	    </div>
	    <!----------------------------------------->
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
	    <div class="buttons">
	      <button type = "submit" class="update-btn" value="update-btn" name = "button" >Update</button>
	      <button type = "submit" class="logout-btn" value="logout-btn" name = "button">Log Out</button>
	    </div>
	  </div>
	</form>
</body>
</html>