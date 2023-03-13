<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
	<link rel="stylesheet" type="text/css" href="Login.css">
</head>
<body>
	<div class="login-container">
		<h1>Login</h1>
		<form action="login" method="post">
			<label for="username">Username:</label>
			<input type="text" id="username" name="username" placeholder="Enter your username">
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" placeholder="Enter your password">
			<label for="type">Login as:</label>
			<select id="type" name="type">
				<option value="customer">Customer</option>
				<option value="store">Store</option>
			</select>
			<input type="submit" value="Login">
		</form>
	</div>
</body>
</html>
