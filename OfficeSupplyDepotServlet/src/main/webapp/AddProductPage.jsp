<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
    <link rel="stylesheet" href="AddProductPage.css">
</head>
<body>
	<div class="header">
		<div class="home-link">
			<a href="MainPage.jsp"> Office Supply Depot </a>
		</div>
    <div class="info-section">
				<a href="AccountPage.jsp">Minh Le</a> | <a href="#">Order</a> | <a href="#">Cart</a>
		</div>
  </div>
    <div class="container">
        <h1>Add Product</h1>
        <form action="AddProductServlet" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
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
                <input type="file" id="image" name="image">
            </div>
            <div class="form-group">
                <button type="submit">Add Product</button>
                <button type="button" onclick="MainPage.jsp'">Cancel</button>
            </div>
        </form>
    </div>
</body>
</html>
