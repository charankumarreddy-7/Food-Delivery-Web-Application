<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Checkout - Food Delivery</title>
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f8f8f8;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 500px;
        margin: 50px auto;
        background-color: #fff;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0 10px 20px rgba(0,0,0,0.1);
    }

    h2 {
        text-align: center;
        color: #FF6347;
        margin-bottom: 25px;
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: 600;
    }

    textarea, select, input[type="text"], input[type="submit"] {
        width: 100%;
        padding: 12px;
        margin-bottom: 20px;
        border-radius: 6px;
        border: 1px solid #ccc;
        font-size: 14px;
    }

    textarea {
        resize: none;
    }

    select {
        background-color: #fff;
    }

    input[type="submit"] {
        background-color: #FF6347;
        color: #fff;
        border: none;
        cursor: pointer;
        font-size: 16px;
        transition: 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #e5533d;
    }
</style>
</head>
<body>

<div class="container">
    <h2>Checkout</h2>
    <form action="checkout" method="post">
        <label for="address">Delivery Address:</label>
        <textarea id="address" name="address" rows="4" placeholder="Enter your delivery address" required></textarea>

        <label for="paymentMethod">Payment Method:</label>
        <select name="paymentMethod" id="paymentMethod" required>
            <option value="" disabled selected>Select Payment Method</option>
            <option value="Credit Card">Credit Card</option>
            <option value="Debit Card">Debit Card</option>
            <option value="UPI">UPI</option>
            <option value="Cash on Delivery">Cash on Delivery</option>
        </select>

        <input type="submit" value="Place Order">
    </form>
</div>

</body>
</html>
    