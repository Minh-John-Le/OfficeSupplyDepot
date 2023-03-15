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
	%>

	<%
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
	%>
	<form action = "account" method="post"> 
		<div class="header">
			<div class="home-link">
				<a href="MainPage.jsp"> Office Supply Depot </a>	
			</div>
			<div class="info-section">
				<a href="Login.jsp">Login</a> | <a href="SignUp.jsp">Sign up</a>
			</div>
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
	        <input type="password" id="password" name="password" value=<%=password%>>
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
	        <input type="text" id="name" name="name" value="">
	      </div>
	      <div class="form-row">
	        <label for="account-number">Account Number:</label>
	        <input type="text" id="account-number" name="account-number" value="">
	      </div>
	      <div class="form-row">
	        <label for="exp">Expiration Date:</label>
	        <input type="text" id="exp" name="exp" value="" placeholder="MM/YY">
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
