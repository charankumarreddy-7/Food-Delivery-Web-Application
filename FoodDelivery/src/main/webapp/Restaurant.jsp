<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.tap.model.Restaurant"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>FoodExpress – Restaurants</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: "Segoe UI", Arial, sans-serif;
	text-decoration: none;
}

body {
	background: black;
	color: red;
}

/* NAVBAR */
.navbar {
	position: sticky;
	top: 0;
	z-index: 100;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 14px 40px;
	background: #ffffff;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
}

.logo {
	font-size: 22px;
	font-weight: 700;
	color: #ff4d4d;
}

.nav-links a {
	margin-left: 24px;
	text-decoration: none;
	color: #333;
	font-weight: 500;
	transition: color 0.3s;
}

.nav-links a:hover {
	color: #ff4d4d;
}

/* CONTAINER */
.container {
	max-width: 1300px;
	margin: 30px auto;
	padding: 0 20px;
}

.page-title {
	font-size: 26px;
	font-weight: 700;
	margin-bottom: 25px;
}

/* GRID */
.restaurant-grid {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
	gap: 28px;
	align-items: stretch;
}

/* CARD */
.card {
	background: #fff;
	border-radius: 16px;
	overflow: hidden;
	box-shadow: 0 4px 14px rgba(0, 0, 0, 0.1);
	transition: 0.3s;
	display: flex;
	flex-direction: column;
}

.card:hover {
	transform: translateY(-6px);
	box-shadow: 0 12px 24px rgba(0, 0, 0, 0.15);
}

.card img {
	width: 100%;
	height: 200px;
	object-fit: cover;
	display: block;
}

.card-body {
	padding: 16px;
	display: flex;
	flex-direction: column;
	gap: 6px;
	flex-grow: 1;
}

.card-title {
	font-size: 18px;
	font-weight: 700;
	margin-bottom: 6px;
}

.rating-eta {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 8px;
}

.rating {
	background: #28a745;
	color: #fff;
	padding: 4px 8px;
	border-radius: 6px;
	font-size: 13px;
	font-weight: 600;
}

.eta {
	font-size: 13px;
	color: #666;
}

.description {
	font-size: 14px;
	color: #555;
}

.location {
	font-size: 13px;
	color: #888;
	font-weight: 600;
}

footer {
	margin-top: 50px;
	padding: 20px;
	text-align: center;
	background: #fff;
	color: #777;
	box-shadow: 0 -2px 8px rgba(0, 0, 0, 0.05);
}

/* Responsive */
@media ( max-width :600px) {
	.navbar {
		flex-direction: column;
		align-items: flex-start;
	}
	.nav-links {
		margin-top: 10px;
	}
}
</style>
</head>
<body>

	<!-- NAVBAR -->
	<div class="navbar">
		<div class="logo">FoodExpress</div>
		<div class="nav-links">
			<a href="#">Home</a> 
			<a href="#">Login</a> 
			<a href="cart">Cart</a> 
			<a href="#">Profile</a>
		</div>
	</div>

	<div class="container">
		<div class="page-title">Popular Restaurants Near You</div>

		<div class="restaurant-grid">

			<%
			List<Restaurant> allRestaurants = (List<Restaurant>) request.getAttribute("allRestaurants");

			for (Restaurant restaurant : allRestaurants) {
			%>

			<a href = "menu?restaurantId=<%= restaurant.getRestaurantId() %> " >
			
				<div class="card">
					<img src="<%=restaurant.getImageUrl()%>"
						alt=" <%=restaurant.getName()%>">
					<div class="card-body">
						<div class="card-title"><%=restaurant.getName()%></div>
						<div class="rating-eta">
							<div class="rating">
								<%=restaurant.getRating()%>
								★
							</div>
							
							<div class="eta"><%=restaurant.getEta()%></div>
						</div>
						<div class="description"><%=restaurant.getCuisineType()%></div>
						<div class="location"><%=restaurant.getAddress()%></div>
					</div>
				</div>
			</a>

			<%
			}
			%>


		</div>
	</div>

	<footer> © 2026 FoodExpress | All Rights Reserved </footer>

</body>
</html>