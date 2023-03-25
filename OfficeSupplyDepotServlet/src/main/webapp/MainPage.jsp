<%@page import = "Beans.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Office Supply Depot</title>
	<link rel="stylesheet" href="MainPage.css">
</head>
<body>
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<div class="search">
			<input type="text" placeholder="Search for items...">
			<button>Search</button>
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
				<a href="AccountPage.jsp"><%=displayName%></a> | <a href="upload.html">Order</a> | <a href="#">Cart</a>
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
	<div class="item-container">
		<div class="item-wrapper">
			<div class="item-image">
				<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			</div>
			<div class="item-details">
				<h3>Paper Clip</h3>
				<p><b>Stock:</b> 100</p>
				<p><b>Weight:</b> 100 lbs</p> 
				<p><b>Price:</b> $10.99</p>
				<p><b>Descrition:</b></p>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sagittis eros eu tellus posuere, vitae sollicitudin elit euismod. Quisque eget leo nulla. Fusce non arcu quis lectus mollis porttitor vel quis nibh. In faucibus blandit lectus sed laoreet.</p>
				<button class="add-to-cart-button" value="1" name = "Add To Cart">Add To Cart</button>
			</div>	
		</div>
		<div class="item-wrapper">
			<div class="item-image">
				<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			</div>
			<div class="item-details">
				<h3>Paper Clip</h3>
				<p><b>Stock:</b> 100</p>
				<p><b>Weight:</b> 100 lbs</p> 
				<p><b>Price:</b> $10.99</p>
				<p><b>Descrition:</b></p>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sagittis eros eu tellus posuere, vitae sollicitudin elit euismod. Quisque eget leo nulla. Fusce non arcu quis lectus mollis porttitor vel quis nibh. In faucibus blandit lectus sed laoreet.</p>
				<button class="add-to-cart-button" value="2" name = "Add To Cart">Add To Cart</button>
			</div>	
		</div>
		<div class="item-wrapper">
			<div class="item-image">
				<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			</div>
			<div class="item-details">
				<h3>Paper Clip</h3>
				<p><b>Stock:</b> 100</p>
				<p><b>Weight:</b> 100 lbs</p> 
				<p><b>Price:</b> $10.99</p>
				<p><b>Descrition:</b></p>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sagittis eros eu tellus posuere, vitae sollicitudin elit euismod. Quisque eget leo nulla. Fusce non arcu quis lectus mollis porttitor vel quis nibh. In faucibus blandit lectus sed laoreet.</p>
				<button class="add-to-cart-button" value="3" name = "Add To Cart">Add To Cart</button>
			</div>	
		</div>
		<div class="item-wrapper">
			<div class="item-image">
				<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			</div>
			<div class="item-details">
				<h3>Paper Clip</h3>
				<p><b>Stock:</b> 100</p>
				<p><b>Weight:</b> 100 lbs</p> 
				<p><b>Price:</b> $10.99</p>
				<p><b>Descrition:</b></p>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sagittis eros eu tellus posuere, vitae sollicitudin elit euismod. Quisque eget leo nulla. Fusce non arcu quis lectus mollis porttitor vel quis nibh. In faucibus blandit lectus sed laoreet.</p>
				<button class="add-to-cart-button" value="4" name = "Add To Cart">Add To Cart</button>
			</div>	
		</div>
	</div>

</body>
</html>
