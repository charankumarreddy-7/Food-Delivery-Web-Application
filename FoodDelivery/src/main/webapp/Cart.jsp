<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.tap.model.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Your Cart</title>
<style>
* {
	box-sizing: border-box;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	margin: 0;
	padding: 0;
}

body {
	background-color: #f9f9f9;
	color: #333;
}

.container {
	max-width: 800px;
	margin: 40px auto;
	background-color: #fff;
	border-radius: 12px;
	box-shadow: 0 8px 20px rgba(0,0,0,0.1);
	padding: 30px;
}

.title {
	text-align: center;
	font-size: 28px;
	font-weight: 600;
	color: #ff6b6b;
	margin-bottom: 30px;
}


.cart-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 20px;
	margin-bottom: 15px;
	background: #f7f7f7;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0,0,0,0.05);
	transition: transform 0.2s;
}

.cart-item:hover {
	transform: translateY(-2px);
}

.details h3 {
	font-size: 18px;
	color: #ff6b6b;
	margin-bottom: 6px;
}

.details p {
	font-size: 14px;
	color: #555;
	margin-bottom: 6px;
}

.qty {
	display: flex;
	align-items: center;
	gap: 8px;
	margin-top: 8px;
}

.qty button {
	width: 32px;
	height: 32px;
	border: none;
	background: #ff6b6b;
	color: #fff;
	border-radius: 6px;
	font-size: 18px;
	cursor: pointer;
	transition: background 0.2s;
}

.qty button:hover {
	background: #ff4b4b;
}

.qty span {
	min-width: 24px;
	text-align: center;
	font-weight: 500;
}

/* Remove Button */
.remove {
	background: #ff4b4b;
	border: none;
	color: #fff;
	padding: 8px 14px;
	border-radius: 6px;
	cursor: pointer;
	font-weight: 500;
	transition: background 0.2s;
}

.remove:hover {
	background: #e63c3c;
}

/* Footer Section */
.footer {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-top: 30px;
	flex-wrap: wrap;
	gap: 15px;
}

.total {
	color: #ff6b6b;
	font-size: 20px;
	font-weight: 600;
}

.add-btn {
	background: #ff6b6b;
	color: #fff;
	border: none;
	padding: 12px 20px;
	border-radius: 8px;
	cursor: pointer;
	font-weight: 500;
	transition: background 0.2s;
}

.add-btn:hover {
	background: #ff4b4b;
}

/* Checkout Button */
.checkout {
	margin-top: 25px;
	width: 100%;
	padding: 14px;
	border: none;
	background: #ff6b6b;
	color: #fff;
	font-size: 16px;
	border-radius: 8px;
	cursor: pointer;
	font-weight: 600;
	transition: background 0.2s;
}

.checkout:hover {
	background: #ff4b4b;
}

/* Responsive */
@media (max-width: 600px) {
	.cart-item {
		flex-direction: column;
		align-items: flex-start;
	}
	.footer {
		flex-direction: column;
		align-items: stretch;
	}
}
</style>
</head>
<body>
	<div class="container">
		<div class="title">Your Cart</div>

		<%
		Cart cart = (Cart) session.getAttribute("cart");
		Integer restaurantId = (Integer) session.getAttribute("oldRestaurantId");
		%>

		<%
		if (cart != null && !cart.getItems().isEmpty()) {
			for (CartItem item : cart.getItems().values()) {
		%>
		<div class="cart-item">
			<div class="details">
				<h3><%= item.getName() %></h3>
				<p>Price: ₹ <%=item.getPrice()%></p>
				<p>Total: ₹ <%= item.getPrice() * item.getQuantity() %></p>
				<div class="qty">
					<form action="cart" style="display:inline;">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
						<input type="hidden" name="quantity" value="<%=item.getQuantity() - 1%>">
						<input type="hidden" name="restaurantId" value="<%=restaurantId%>">
						<button type="submit">-</button>
					</form>

					<span><%= item.getQuantity() %></span>

					<form action="cart" style="display:inline;">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
						<input type="hidden" name="quantity" value="<%=item.getQuantity() + 1%>">
						<input type="hidden" name="restaurantId" value="<%=restaurantId%>">
						<button type="submit">+</button>
					</form>
				</div>
			</div>

			<form action="cart" style="display:inline;">
				<input type="hidden" name="action" value="remove">
				<input type="hidden" name="itemId" value="<%=item.getItemId()%>">
				<input type="hidden" name="restaurantId" value="<%=restaurantId%>">
				<button class="remove" type="submit">Remove</button>
			</form>
		</div>
		<% } 
			}
		else {
		%>
		<h2>Your cart is empty</h2>
		<% } %>

		<div class="footer">
		<%
		boolean isEmpty = ((cart == null) || cart.getItems().isEmpty());
		%>
			<form action="menu">
				<input type="hidden" name="restaurantId" value="<%=restaurantId%>">
				<button class="add-btn" type="submit"><%= isEmpty? "Add items" : "Add More Items" %></button>
			</form>
			<div class="total">Grand Total: ₹ <%= cart != null ? cart.getTotalPrice() : 0 %></div>
		</div>

		<form action="checkout" method="post">
		<button class="checkout">Proceed to Checkout</button>
		</form>
	</div>
</body>
</html>
