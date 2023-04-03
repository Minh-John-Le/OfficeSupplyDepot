<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Place Order </title>
	<link rel="stylesheet" href="PlaceOrderPage.css">
</head>
<body>
	<form action = "pastorderpage" method="post"> 
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

		
	</div>

	<!-- 
		UNFINISHED: Group items by Date and sort
	-->

		
	</div>
	<div class="orders-title">
		<h3> Place Order </h3>
		</div>
	<div class="item-container">

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
			</div>	
		</div>

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
			</div>	
		</div>

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
		</div>

	</div>

		<button class="place-order-button" value="<place-order>" name = "Place-Order">Place Order</button>
		<button class="cancel-button" value="<cancel-order>" name = "Cancel"> Cancel </button>

	</form>
</body>
</html>
