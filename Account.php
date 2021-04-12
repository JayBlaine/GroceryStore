<?php
echo'<html lang="en">';
echo'<head>';
  echo'<title>Bootstrap Example</title>';
  echo'<meta charset="utf-8">';
  echo'<meta name="viewport" content="width=device-width, initial-scale=1">';
  echo'<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">';
  echo'<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>';
  echo'<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>';
  echo'<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>';
echo'</head>';
echo'<body>';
echo'<div class = "jumbotron tetx-center" style="margin-bottom:0">';
	echo'<h1>Grocery Store</h1>';
echo'</div>';
echo'<nav class="navbar navbar-expand-sm bg-dark navbar-dark">';
	echo'<a class="navbar-brand" href="index.php">Navbar</a>';
	echo'<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">';
		echo'<span class="navbar-toggler-icon"></span>';
	echo'</button>';
	echo'<div class="collapse navbar-collapse" id="collapsibleNavbar">';
		echo'<button class="btn text-muted" id="login" type="button"  data-toggle="modal" data-target="#loginModal">Login</button>';
		echo'<button class="btn text-muted" id="signup" type="button" data-toggle="modal" data-target="#signupModal">Signup</button>';
		echo'<button type="button" class="btn text-muted" data-toggle="modal" data-target="#cartModal">View Cart</button>';
		echo'<a class="nav-link" href="Account.php">Account</a>';	
	echo'</div>';
echo'</nav>';
echo'<div class="container" style="margin-top:30px">';
	echo'<div class="row">';
	echo'<h2>Account Details</h2>';
	echo'<p>Email:</p>';
	echo'<p>Full Name:</p>';
	echo'<p>Phone:</p>';
	echo'<p>Address:</p>';
	
echo'</body>';
echo'</html>';
?>