<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" href="UpdateProductPage.css">
</head>
<body>
	<%

	OSDAdmin loginAdmin = (OSDAdmin) session.getAttribute("loginAdmin");
	
	// information
	String username = "";
	String email = "";
	String password = "";
	String displayName = "";

	if (loginAdmin == null)
	{
		%>
		<script type="text/javascript">
			window.location.href = "MainPage.jsp";
		</script>
			<p>If you are not redirected automatically, please click <a href="MainPage.jsp">here</a></p>
		<%
	}
	
	if (loginAdmin != null)
	{
		username = loginAdmin.getUsername();
		email = loginAdmin.getEmail();
		password = loginAdmin.getPassword();
		displayName = loginAdmin.getAdminName();
	}

	%>
	<form action = "updateproduct" method="post" enctype="multipart/form-data"> 
		<div class="header">
			<div class="home-link">
				<a href="MainPage.jsp"> Office Supply Depot </a>	
			</div>
			<%
				if (loginAdmin != null)
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
		<!-- ----------------------------------------------------------------------------- -->
		
		<%
		Product updateProduct = (Product) session.getAttribute("updateProduct");
		
		String productName = updateProduct.getName();
        int warehouseId = updateProduct.getWarehouse_id();
        String warehouseIDStr = String.valueOf(warehouseId);
        int stock = updateProduct.getStock();
        BigDecimal weight = updateProduct.getWeight();
        String description = updateProduct.getDescription();
        BigDecimal price = updateProduct.getPrice();
        String barcode = updateProduct.getBarcode();
        String category = updateProduct.getCategory();
        String imageURL = updateProduct.getImageURL();
		
		%>
	    <div class="container">
        	<h1>Update Product</h1>       	
        	<div class="form-group">
                <label for="name">Barcode:</label>
                <input type="text" id="barcode" name="barcode" required value = "<%=barcode%>" readonly>
            </div>
            
            <hr>
            <br>
            
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required value ="<%=productName%>">
            </div>
         
            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" name="category" required>
                    <option value="Electronics" <%= category.equals("Electronics") ? "selected" : "" %>>Electronics</option>
                    <option value="Craft Supplies" <%= category.equals("Craft Supplies") ? "selected" : "" %>>Craft Supplies</option>
                    <option value="Office Furniture" <%= category.equals("Office Furniture") ? "selected" : "" %>>Office Furniture</option>
                    <option value="Essentials" <%= category.equals("Essentials") ? "selected" : "" %>>Essentials</option>
                </select>
            </div>
            <div class="form-group">
                <label for="warehouse">Warehouse:</label>
                <select id="warehouse" name="warehouse" required>
                    <option value="1" <%= warehouseIDStr.equals("1") ? "selected" : "" %>>Warehouse 1</option>
                    <option value="2" <%= warehouseIDStr.equals("2") ? "selected" : "" %>>Warehouse 2</option>
                </select>
            </div>
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="number" id="stock" name="stock" required value = <%=stock%> min="0" max = "1000000">
            </div>
            <div class="form-group">
                <label for="weight">Weight:</label>
                <input type="number" step="0.01" id="weight" name="weight" required value = <%=weight%> min="0" max = "1000000">
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description"  maxlength="200" placeholder="Maximum 200 characters" required><%=description%></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" step="0.01" id="price" name="price" required value =<%=price%> min="0" max = "1000000">
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <div class="item-image">
					<img src="<%=imageURL%>">
				</div>
				<input type="checkbox" id="myCheckbox" name="myCheckbox" value="true"> New Image
                <input type="file" id="file" name="file">
            </div>
            <div class="form-group">
                <button type="submit" value="Update Product" name = "button">Update Product</button>
                <button type="button" onclick="window.location.href = 'InventoryPage.jsp';">Cancel</button>
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
