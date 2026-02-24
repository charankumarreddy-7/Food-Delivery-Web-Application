<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Login</title>

<link rel="stylesheet" href="style.css">
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap"
	rel="stylesheet">

<!-- Font Awesome Icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">


</head>
<body>

	<div class="container">
		<h2>Welcome Back</h2>

		<% String error = (String) request.getAttribute("errorMessage");
           if(error != null){ %>
		<div class="error"><%= error %></div>
		<% } %>
		<form action="LoginServlet" method="post">

			<div class="input-box">
				<i class="fa fa-user"></i> <input type="text" name="name"
					placeholder="Enter Username" required>
			</div>

			<div class="input-box">
				<i class="fa fa-lock"></i> <input type="password" name="password"
					placeholder="Enter Password" required>
			</div>

			<button type="submit">Login</button>

			<div class="register">
				Not Registered? <a href="Register.html">Create Account</a>
			</div>

		</form>
	</div>

</body>