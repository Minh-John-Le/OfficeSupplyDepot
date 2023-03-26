<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Office Supply Depot</title>
	<link rel="stylesheet" href="MainPage.css">
</head>
<body>
	<form action = "mainpage" method="post"> 
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<div class="form-group">
            <select id="category" name="category" required>
            	<option value="All">All</option>
                <option value="Electronics">Electronics</option>
                <option value="Craft Supplies">Craft Supplies</option>
                <option value="Office Furniture">Office Furniture</option>
                <option value="Essentials">Essentials</option>
            </select>
        </div>
		<div class="search">
			<input type="text" placeholder="Search for items..." name = "search text">
			<button name = "button" value = "search">Search</button>
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
	
	<%
      	LinkedList<Product> searchProductList = (LinkedList<Product>) session.getAttribute("searchProductList");      		
      	if(searchProductList != null)
      	{
      		for(int i = 0 ; i < searchProductList.size(); i++)
      		{
      			String imageUrl = searchProductList.get(i).getImageURL();
      			String productname = searchProductList.get(i).getName();
      			int stock = searchProductList.get(i).getStock();
      			BigDecimal weight = searchProductList.get(i).getWeight();
      			BigDecimal price = searchProductList.get(i).getPrice();
      			String description = searchProductList.get(i).getDescription();
      			int productId = searchProductList.get(i).getId();	
      			
    %>
    				
		<div class="item-wrapper">
			<div class="item-image">
				<img src="<%=imageUrl%>">
			</div>
			<div class="item-details">
				<h3><%=productname%></h3>
				<p><b>Stock:</b> <%=stock%></p>
				<p><b>Weight:</b> <%=weight%> lbs</p> 
				<p><b>Price:</b> $<%=price%></p>
				<p><b>Description:</b></p>
				<p><%=description%></p>
				<br>
				<br>
				<button class="add-to-cart-button" value="<%=productId%>" name = "Add To Cart">Add To Cart</button>
			</div>	
		</div>
	<%
			}
      	} 
    %>
 
	</div>
	</form>
</body>
</html>
