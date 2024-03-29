<%@page import = "java.util.List" %>
<%@page import = "java.util.Iterator" %>
<%@page import = "java.util.LinkedList" %>
<%@page import = "Beans.*" %>
<%@page import = "java.math.BigDecimal" %>
<!DOCTYPE html>
<html>
 	 <head>
  	<meta charset="UTF-8">
  		<title>Contact Page</title>
		<link rel="stylesheet" type="text/css" href="ContactPage.css">
	</head>
	  <body>
	  	<div class="header">
	  		<div class="home-link">
	  			<a href="MainPage.jsp"> Office Supply Depot </a>
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
	<main>
		<h1>Our Team Members</h1>
		<div class="team-container">
			<div class="team-member">
				<h2>Kaleigh Mikowicz</h2>
				<p class="skills">Full-stack Developer</p>
				<p class="email">kaleigh.mikowicz@sjsu.edu</p>
				<a href="https://www.linkedin.com/in/kaleigh-mikowicz/" target="_blank">LinkedIn Profile</a>
			</div>
			<div class="team-member">
				<h2>Minh Le</h2>
				<p class="skills">Full-stack Developer, Data Scientist</p>
				<p class="email">hungle0614@gmail.com</p>
				<a href="https://www.linkedin.com/in/minh-le-software-engineer/" target="_blank">LinkedIn Profile</a>
			</div>
			<div class="team-member">
				<h2>Nate Agpaoa</h2>
				<p class="skills">Software QA</p>
				<p class="email">nathaniellouisagpaoa@yahoo.com</p>
				<a href="https://www.linkedin.com/in/nathaniel-louis-agpaoa/" target="_blank">LinkedIn Profile</a>
			</div>
			<div class="team-member">
				<h2>Nathan Wong</h2>
				<p class="skills">Computer Scientist</p>
				<p class="email">ngjwong@yahoo.com</p>
				<a href="https://www.linkedin.com/in/nathan-wong-a4b422112/" target="_blank">LinkedIn Profile</a>
			</div>
      <div class="team-member">
				<h2>Khush Naidu</h2>
				<p class="skills">Software Engineer</p>
				<p class="email">khush.naidu@gmail.com</p>
				<a href="https://www.linkedin.com/in/khush-naidu/" target="_blank">LinkedIn Profile</a>
			</div>
      <div class="team-member">
				<h2>David Warshawsky</h2>
				<p class="skills">Data and BioTechnology Engineer</p>
				<p class="email">davidawarshawsky@gmail.com</p>
				<a href="https://www.linkedin.com/in/david-a-warshawsky/" target="_blank">LinkedIn Profile</a>
			</div>
		</div>
	</main>

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
