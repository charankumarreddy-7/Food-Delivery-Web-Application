<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, com.tap.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Placed Successfully</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background: #f2f8ff;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        background: #fff;
        padding: 40px;
        border-radius: 12px;
        box-shadow: 0 10px 25px rgba(0,0,0,0.1);
        text-align: center;
        max-width: 500px;
        width: 90%;
    }

    .checkmark {
        font-size: 60px;
        color: #4BB543;
        margin-bottom: 20px;
    }

    h1 {
        color: #333;
        margin-bottom: 10px;
    }

    p {
        color: #666;
        margin-bottom: 30px;
    }

    .order-details {
        text-align: left;
        margin-bottom: 30px;
    }

    .order-details h3 {
        margin-bottom: 10px;
        color: #444;
    }

    .order-details p {
        margin: 5px 0;
        color: #555;
    }

    .btn {
        display: inline-block;
        background: #4BB543;
        color: #fff;
        padding: 12px 25px;
        text-decoration: none;
        border-radius: 6px;
        font-weight: bold;
        transition: background 0.3s;
    }

    .btn:hover {
        background: #3a9235;
    }
</style>
</head>
<body>

<div class="container">
    <div class="checkmark">✔</div>
    <h1>Order Placed Successfully!</h1>
    <p>Thank you for your order. Your delicious food is on its way!</p>

    <div class="order-details">
        <h3>Order Summary</h3>
        
        <%
        Map<Integer, CartItem> items = (Map<Integer, CartItem>) session.getAttribute("items");
    		if(items != null){
    			for(CartItem item: items.values()){
    				
        
        %>
        <p><strong>Items:</strong> <%= item.getName() %></p>
       
       <%
        }
       } 
       %>
        
        
        <p><strong>Order ID: </strong><%= session.getAttribute("orderId") %></p>
        <p><strong>Total Amount:</strong> <%=  session.getAttribute("totalAmount") %></p>
        <p><strong>Delivery Time:</strong> 10-20 minutes</p>
    </div>

    <a href="Restaurant" class="btn">Back to Menu</a>
</div>

</body>
</html>
