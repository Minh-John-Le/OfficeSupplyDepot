<%@page import = "beans.*" %>
<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" href="AddProductPage.css">
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
	<form action = "addproduct" method="post" enctype="multipart/form-data"> 
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
	    <div class="container">
        	<h1>Add Product</h1>
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="form-group">
                <label for="name">Barcode:</label>
                <input type="text" id="barcode" name="barcode" required>
            </div>
            <div class="form-group">
                <label for="category">Category:</label>
                <select id="category" name="category" required>
                    <option value="Electronics">Electronics</option>
                    <option value="Craft Supplies">Craft Supplies</option>
                    <option value="Office Furniture">Office Furniture</option>
                    <option value="Essentials">Essentials</option>
                </select>
            </div>
            <div class="form-group">
                <label for="warehouse">Warehouse:</label>
                <select id="warehouse" name="warehouse" required>
                    <option value="1">Warehouse 1</option>
                    <option value="2">Warehouse 2</option>
                </select>
            </div>
            <div class="form-group">
                <label for="stock">Stock:</label>
                <input type="number" id="stock" name="stock" required>
            </div>
            <div class="form-group">
                <label for="weight">Weight:</label>
                <input type="number" step="0.01" id="weight" name="weight" required>
            </div>
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" required></textarea>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" step="0.01" id="price" name="price" required>
            </div>
            <div class="form-group">
                <label for="image">Image:</label>
                <input type="file" id="file" name="file">
            </div>
            <div class="form-group">
                <button type="submit" value="Add Product" name = "button">Add Product</button>
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
		<div class="home-link">
			<a href="ContactPage.jsp">Contact us</a> 
			<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
			<a href="UserGuide.jsp">Help</a> 
		</div>
	
		<p>&copy; 2023 San Jose State University CS160-Team10. All Rights Reserved.</p>
	</footer>
</html>
