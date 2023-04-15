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
<%
	SearchProductFilter searchProductFilter = (SearchProductFilter) session.getAttribute("searchProductFilter");
	String category = "";
	String sortBy = "";
	String searchTerm = "";
	if (searchProductFilter != null)
	{
		category = searchProductFilter.getCategory();
		sortBy = searchProductFilter.getSortBy();
		searchTerm = searchProductFilter.getSearchTerm();
	}
%>
	<form action = "mainpage" method="post"> 
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>	
		</div>
		<div class="form-group">
            <select id="category" name="category" required>
            	<option value="All" <%= category.equals("All") ? "selected" : "" %>>All</option>
                <option value="Electronics" <%= category.equals("Electronics") ? "selected" : "" %>>Electronics</option>
                <option value="Craft Supplies" <%= category.equals("Craft Supplies") ? "selected" : "" %>>Craft Supplies</option>
                <option value="Office Furniture" <%= category.equals("Office Furniture") ? "selected" : "" %>>Office Furniture</option>
                <option value="Essentials" <%= category.equals("Essentials") ? "selected" : "" %>>Essentials</option>
            </select>
        	<select id="category" name="sortBy" required>
	       	   <option value="Name ASC" <%= sortBy.equals("Name ASC") ? "selected" : "" %> >A-Z</option>
	           <option value="Name DESC" <%= sortBy.equals("Name DESC") ? "selected" : "" %>>Z-A</option>
	           <option value="Stock ASC" <%= sortBy.equals("Stock ASC") ? "selected" : "" %>>Least Stock</option>
	           <option value="Stock DESC" <%= sortBy.equals("Stock DESC") ? "selected" : "" %>>Most Stock</option>
	           <option value="Price ASC" <%= sortBy.equals("Price ASC") ? "selected" : "" %>> Lowest Price</option>
	           <option value="Price DESC" <%= sortBy.equals("Price DESC") ? "selected" : "" %>>Highest Price</option>
	       </select> 
        </div>
        
		<div class="search">
			<input type="text" placeholder="Search for items..." name = "search text" value ="<%=searchTerm%>">
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
	
	<br>
	<div class="item-container">
	
	<%
      	LinkedList<Product> searchProductList = (LinkedList<Product>) session.getAttribute("searchProductList");      		
      	if(searchProductList != null && searchProductList.size() > 0)
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
      			
      			//Temporary, so I can use in the MainPage.servlet to get product in cart
      			String productBarcode = searchProductList.get(i).getBarcode();
      			
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
				<!-- Original -->
				<%-- <button class="add-to-cart-button" value="<%=productID%>" name = "Add To Cart">Add To Cart</button> --%>
				<!-- Barcode version -->
				<%
				if (loginCustomer != null)
				{
				%>
				<button class="add-to-cart-button" value="<%=productBarcode%>" name = "Add To Cart">Add To Cart</button>
				<%
				}
				%>
			</div>	
		</div>
	<%
			}
      	}
    
    %>
 
	</div>
	<%
	  	if (searchProductList == null || searchProductList.size() <= 0)
      	{
    %>
      		<div class="card">
			  <div class="card-body">
			    <h5 class="card-title">Welcome to Office Supply Depot</h5>
			    <p class="card-text">This is the project of students in team 10 from San Jose State University</p>
			    <p class="card-text">To get started, simply follow the guide below:</p>
			    <ul>
			      <li><b>Full user guide:</b> click on "help" in footer to view full user guide</li>
			      <li><b>View products:</b> Simply click "search" button on header to view all product or add filter to search for a specific product.</li>
			      <li><b>Login / Sign up:</b> Click "Sign up"" to create new account or "login" with your credentials for more functions.</li>
			      <li><b>Contact dev team:</b> click on "Contact us" in footer.</li>
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
