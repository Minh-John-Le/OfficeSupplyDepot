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
		<div class="item-box">
			<img src="/home/shadowsong/Documents/GitHub/OfficeSupplyDepot/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/OfficeSupplyDepotServlet//Resources/15.png">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
		<div class="item-box">
			<img src="/home/shadowsong/Documents/GitHub/OfficeSupplyDepot/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/OfficeSupplyDepotServlet//Resources/15.png">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
		<div class="item-box">
			<img src="Resources/Rick1.jpg">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
		<div class="item-box">
			<img src="https://dummyimage.com/200x200/000/fff&text=Paperclips">
			<h3>Paper Clip</h3>
			<p>Rating 5/5</p>
			<p>$10.99</p>
		</div>
	</div>
</body>
</html>
