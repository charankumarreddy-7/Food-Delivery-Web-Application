<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.tap.model.Menu"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Food Menu</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background: linear-gradient(to right, #fdfbfb, #ebedee);
}

h1 {
	text-align: center;
	padding: 20px;
	color: #333;
}

.menu-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
	gap: 25px;
	padding: 20px 40px;
}

.menu-card {
	background: #fff;
	border-radius: 15px;
	box-shadow: 0 10px 25px rgba(0, 0, 0, 0.12);
	overflow: hidden;
	transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.menu-card:hover {
	transform: translateY(-8px);
	box-shadow: 0 18px 35px rgba(0, 0, 0, 0.18);
}

.menu-card img {
	width: 100%;
	height: 200px;
	object-fit: cover;
}

.menu-content {
	padding: 15px 18px;
}

.menu-content h3 {
	margin: 0;
	color: #222;
}

.menu-content p {
	font-size: 14px;
	color: #666;
	margin: 8px 0 12px;
}

.details {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.price {
	font-size: 16px;
	font-weight: bold;
	color: #2e7d32;
}

.rating {
	background: #388e3c;
	color: #fff;
	padding: 4px 8px;
	border-radius: 8px;
	font-size: 13px;
}

.add-cart {
	width: 100%;
	border: none;
	background: linear-gradient(to right, #ff512f, #dd2476);
	color: white;
	padding: 12px;
	font-size: 15px;
	cursor: pointer;
	transition: background 0.3s ease;
}

.add-cart:hover {
	background: linear-gradient(to right, #dd2476, #ff512f);
}

input[type="submit"]{

padding: 0.5rem;
margin: 0.5rem;
background-color: green;
color: white;
cursor: pointer;

}

</style>
</head>

<body>

	<h1>🍽️ Our Special Menu</h1>

	<div class="menu-container">

		<%
		List<Menu> allMenus = (List<Menu>) request.getAttribute("allMenusByRestaurant");
		if(allMenus != null && !allMenus.isEmpty()){
			for (Menu menu : allMenus) {
		%>
		
		
		
		<div class="menu-card">
			<img src="<%=menu.getImage_url()%>" alt="<%=menu.getName()%>">
			<div class="menu-content">
				<h3>
					<%=menu.getName()%>
				</h3>
				<p>
					<%=menu.getDescription()%>
				</p>
				<div class="details">
					<span class="price"><%=menu.getPrice()%>
					</span> <span class="rating">⭐<%=menu.getRating()%></span>
				</div>
			</div>

			<form action="cart">
				<input type="hidden" name="itemId" value="<%=menu.getMenuId()%>">
				<input type="hidden" name="restaurantId" value="<%=menu.getRestaurantId()%>">
					<input type="hidden" name="quantity" value="1"> 
					<input type="hidden" name="action" value="add">
					<input type="submit" value="Add To Cart" >

			</form>


		</div>

		<%
		}
		}
			else {
		%>		
			<p>No menus available</p>
		<%
			}
		%>
	</div>

</body>
</html>
