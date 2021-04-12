<?php
echo'<html lang="en">';
echo'<head>';
  echo'<title>Grocery Store</title>';
  echo'<meta charset="utf-8">';
  echo'<meta name="viewport" content="width=device-width, initial-scale=1">';
  echo'<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">';
  echo'<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>';
  echo'<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>';
  echo'<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>';
  echo'<style>';
  echo'.fakeimg {';
   echo' height: 200px;';
   echo' background: #aaa;';
 echo' }';
  echo'</style>';
echo'</head>';
echo'<body>';
echo'<!--Top of website with header-->';
echo'<div class = "jumbotron tetx-center" style="margin-bottom:0">';
	echo'<h1>Grocery Store</h1>';
echo'</div>';
echo'<!--Navbar for logging in/ or changing which items to view-->';
echo'<nav class="navbar navbar-expand-sm bg-dark navbar-dark">';
	echo'<a class="navbar-brand" href="index.php">Home</a>';
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

echo'<!--Put modals here-->';
 echo'<!-- The login Modal -->';
 echo'<form id="loginform" method="post" action="">';
 echo'<div class="container mt-3">';
  echo'<div class="modal fade" id="loginModal">';
    echo'<div class="modal-dialog">';
      echo'<div class="modal-content">';
      
        echo'<!-- Modal Header -->';
        echo'<div class="modal-header">';
          echo'<h4 class="modal-title">Login</h4>';
          echo'<button type="button" class="close" data-dismiss="modal">×</button>';
        echo'</div>';
        
        echo'<!-- Modal body -->';
        echo'<div class="modal-body">';
          echo'<div class="form-group">';
			echo'<label for="emailInput">Email address</label>';
			echo'<input type="email" class="form-control" id="emailInput" placeholder="Enter email">';
		  echo'</div>';
		  echo'<div class="form-group">';
			echo'<label for="passInput">Password</label>';
			echo'<input type="password" class="form-control" id="passInput" placeholder="Enter password">';
		  echo'</div>';
        echo'</div>';
        
        echo'<!-- Modal footer -->';
        echo'<div class="modal-footer">';
		echo'<div class="form-group">';
                echo'<input type="submit" name="submit" value="Login" class="btn btn-block btn-danger">';
         echo'</div>';
          echo'<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>';
		 echo'<!-- <button id="login1" onclick="login1()" class="btn btn-width bkgrnd-cyan save-details" type="button" name="save-details">Login</button>-->';
        echo'</div>';
        
      echo'</div>';
    echo'</div>';
  echo'</div>';
 echo'</div>';
 echo'</form>';
 
echo'<!--The signup modal-->';
echo'<form method="post" id="singnupFrom" onSubmit="return validation();" action="">';
 echo'<div class="container mt-3">';
  echo'<div class="modal fade" id="signupModal">';
    echo'<div class="modal-dialog">';
      echo'<div class="modal-content">';
      
        echo'<!-- Modal Header -->';
        echo'<div class="modal-header">';
          echo'<h4 class="modal-title">SignUp</h4>';
         echo'<button type="button" class="close" data-dismiss="modal">×</button>';
        echo'</div>';
        
        echo'<!-- Modal body -->';
        echo'<div class="modal-body">';
             echo'<div class="form-group">';
                echo'<label class="font-weight-bold">Email</label>';
                    echo'<div class="input-group">';
                        echo'<input type="email" name="email" id="email" class="form-control" placeholder="Enter valid email">';
                            echo'<div class="input-group-append"><button type="button" class="btn btn-primary" onClick="return emailCheck();"><i class="fa fa-envelope"></i></button></div>';
                    echo'</div>';
            echo'</div>';
            
                echo'<div class="form-group">';
                    echo'<label class="font-weight-bold">User Name</label>';
                     echo'<input type="text" name="username" id="username" class="form-control" placeholder="First Last">';
                echo'</div>';
                echo'<div class="form-group">';
                            echo'<label class="font-weight-bold">Phone #</label>';
                            echo'<input type="text" name="phone" id="phone" class="form-control" placeholder="(000)-(0000000)">';
                echo'</div>';
				echo'<div class="form-group">';
					echo'<label class="font-weight-bold">Address</label>';
					echo'<input type="text" name="address" id="address" class="form-control" placeholder="Street address | City | State | Zip">';
				echo'</div>';
                echo'<div class="form-group">';
                    echo'<label class="font-weight-bold">Password</label>';
                    echo'<input type="password" name="password" id="password" class="form-control" placeholder="***********">';
                echo'</div>';
                echo'<div class="form-group">';
                    echo'<label class="font-weight-bold">Confirm Password</label>';
                   echo' <input type="password" name="cpassword" id="cpassword" class="form-control" placeholder="***********">';
                       echo'<em id="cp"></em>';
                echo'</div>';
                echo'<div class="form-group">';
                    echo'<label><input type="checkbox" name="condition" id="condition"> I agree with the <a href="javascript:;">Terms & Conditions</a> for Registration.</label>';
                echo'</div>';
        echo'</div>';
        
        echo'<!-- Modal footer -->';
        echo'<div class="modal-footer">';
		 echo'<div class="form-group">';
                echo'<input type="submit" name="submit" value="Sign Up" class="btn btn-block btn-danger">';
         echo'</div>';
          echo'<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>';
		  echo'<!--<button id="save" onclick="save()" class="btn btn-width bkgrnd-cyan save-details" type="button" name="save-details">Signup</button>-->';
        echo'</div>';
        
      echo'</div>';
    echo'</div>';
  echo'</div>';
 echo'</div>';
 echo'</form>';
 
 
 echo'<!--Cart Modal-->';
 echo'<div class="modal fade" id="cartModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">';
  echo'<div class="modal-dialog modal-lg modal-dialog-centered" role="document">';
    echo'<div class="modal-content">';
      echo'<div class="modal-header border-bottom-0">';
        echo'<h5 class="modal-title" id="exampleModalLabel">Your Shopping Cart</h5>';
        echo'<button type="button" class="close" data-dismiss="modal" aria-label="Close">';
          echo'<span aria-hidden="true">&times;</span>';
        echo'</button>';
      echo'</div>';
      echo'<div class="modal-body">';
        echo'<table class="table table-image">';
          echo'<thead>';
            echo'<tr>';
              echo'<th scope="col"></th>';
              echo'<th scope="col">Product</th>';
              echo'<th scope="col">Price</th>';
              echo'<th scope="col">Qty</th>';
              echo'<th scope="col">Total</th>';
              echo'<th scope="col">Actions</th>';
            echo'</tr>';
          echo'</thead>';
          echo'<tbody>';
            echo'<tr>';
              echo'<td class="w-25">';
			  echo'<img src="image" class="fakeimg" alt="Sheep">';
              echo'</td>';
              echo'<td>Name of item</td>';
              echo'<td>$currentPrice</td>';
              echo'<td class="qty"><input type="text" class="form-control" id="item1" value="0"></td>';
              echo'<td>178$</td>';
              echo'<td>';
                echo'<a href="#" class="btn btn-danger btn-sm">';
                  echo'<i class="fa fa-times"></i>';
                echo'</a>';
              echo'</td>';
            echo'</tr>';
          echo'</tbody>';
        echo'</table>';
        echo'<div class="d-flex justify-content-end">';
          echo'<h5>Total: <span class="price text-success">89$</span></h5>';
        echo'</div>';
      echo'</div>';
      echo'<div class="modal-footer border-top-0 d-flex justify-content-between">';
        echo'<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>';
        echo'<button type="button" class="btn btn-success">Checkout</button>';
      echo'</div>';
    echo'</div>';
  echo'</div>';
echo'</div>';
echo'<!--Start to  list avaliable items for sale-->';
echo'<div class="container" style="margin-top:30px">';
	echo'<div class="row">';
		echo'<div class="col-sm-4">';
			echo'<h2>Apples</h2>';
			echo'<div class="fakeimg">Temp Image</div>';
			echo'<p>Some facts or info about item</p>';
			echo'<div class="col-sm-8">';
                echo'<div class="input-group">';
                    echo'<span class="input-group-btn">';
                   echo' <button type="button" class="quantity-left-minus btn btn-danger btn-number"  data-type="minus" data-field="">';
                        echo'<span class="glyphicon glyphicon-minus"></span>';
                    echo'</button>';
                    echo'</span>';
                    echo'<input type="text" id="quantity" name="quantity" class="form-control input-number" value="10" min="1" max="100">';
                        echo'<span class="input-group-btn">';
                        echo'<button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus" data-field="">';
                            echo'<span class="glyphicon glyphicon-plus"></span>';
                        echo'</button>';
						echo'<button type="button" class="btn btn-primary" id="save2">Add To Cart</button>';
                        echo'</span>';
                echo'</div>';
            echo'</div>';
			echo'<br>';
			echo'<div class="col-sm-14">';
			echo'<h2>Bananas</h2>';
			echo'<div class="fakeimg">Temp Image</div>';
			echo'<p>Some fact or info about this product</p>';
			echo'<div class="col-sm-14">';
                echo'<div class="input-group">';
                    echo'<span class="input-group-btn">';
                    echo'<button type="button" class="quantity-left-minus btn btn-danger btn-number"  data-type="minus" data-field="">';
                        echo'<span class="glyphicon glyphicon-minus"></span>';
                    echo'</button>';
                    echo'</span>';
                    echo'<input type="text" id="quantity1" name="quantity" class="form-control input-number" value="10" min="1" max="100">';
                        echo'<span class="input-group-btn">';
                        echo'<button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus" data-field="">';
                            echo'<span class="glyphicon glyphicon-plus"></span>';
                        echo'</button>';
						echo'<button type="button" class="btn btn-primary" id="save2">Add To Cart</button>';
                        echo'</span>';
                echo'</div>';
            echo'</div>';
			echo'</div>';
			echo'<br>';
			echo'<div class="col-sm-14">';
			echo'<h2>Oranges</h2>';
			echo'<div class="fakeimg">Temp Image</div>';
			echo'<p>Some fact or info about this product</p>';
			echo'<div class="col-sm-14">';
                echo'<div class="input-group">';
                    echo'<span class="input-group-btn">';
                    echo'<button type="button" class="quantity-left-minus btn btn-danger btn-number"  data-type="minus" data-field="">';
                       echo'<span class="glyphicon glyphicon-minus"></span>';
                    echo'</button>';
                    echo'</span>';
                    echo'<input type="text" id="quantity2" name="quantity" class="form-control input-number" value="10" min="1" max="100">';
                        echo'<span class="input-group-btn">';
                        echo'<button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus" data-field="">';
                            echo'<span class="glyphicon glyphicon-plus"></span>';
                        echo'</button>';
						echo'<button type="button" class="btn btn-primary" id="save2">Add To Cart</button>';
                        echo'</span>';
                echo'</div>';
            echo'</div>';
			echo'</div>';
			echo'<br>';
		echo'</div>';
    echo'</div>';
 echo'</div>';	
echo'</body>';
?>
<script>
$(document).ready(function(){
var quantitiy=0;
   $('.quantity-right-plus').click(function(e){       
        // Stop acting like a button
        e.preventDefault();
        // Get the field name
        var quantity = parseInt($('#quantity').val());       
        // If is not undefined           
            $('#quantity').val(quantity + 1);         
            // Increment       
    });
     $('.quantity-left-minus').click(function(e){
        // Stop acting like a button
        e.preventDefault();
        // Get the field name
        var quantity = parseInt($('#quantity').val());       
        // If is not undefined
            // Increment
            if(quantity>0){
            $('#quantity').val(quantity - 1);
            }
    });   
});
//form validation before saving data
 function emailCheck(){
        if($("#email").val()==""){
            $("#email").addClass('is-invalid');
            return false;
        }else{
            var regMail     =   /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;
            if(regMail.test($("#email").val()) == false){
                $("#email").addClass('is-invalid');
                return false;
            }else{
                $("#email").removeClass('is-invalid');
                $('#next-form').collapse('show');
            }
 
        }
    }
    function validation(){
        if($("#username, #phone, #address, #password, #cpassword").val()==""){
            $("#username, #phone, #address, #password, #cpassword").addClass('is-invalid');
            return false;
        }else{
            $("#username, #phone, #address, #password, #cpassword").removeClass('is-invalid');
        }
         
        if($("#password").val()!=$("#cpassword").val()){
            $("#cpassword").addClass('is-invalid');
            $("#cp").html('<span class="text-danger">Password and confirm password not matched!</span>');
            return false;
        }
    }
    $(document).ready(function(e) {
        $("#username").on("keyup",function(){
            if($("#username").val()==""){
                $("#username").addClass('is-invalid');
                return false;
            }else{
                $("#username").removeClass('is-invalid');
            }
        });
        $("#phone").on("keyup",function(){
            if($("#phone").val()==""){
                $("#phone").addClass('is-invalid');
                return false;
            }else{
                $("#phone").removeClass('is-invalid');
            }
        });
		$("#address").on("keyup",function(){
			if($("#address").val()==""){
                $("#address").addClass('is-invalid');
                return false;
            }else{
                $("#address").removeClass('is-invalid');
            }
		});
        $("#password").on("keyup",function(){
            if($("#password").val()==""){
                $("#password").addClass('is-invalid');
                return false;
            }else{
                $("#password").removeClass('is-invalid');
            }
        });
        $("#cpassword").on("keyup",function(){
            if($("#cpassword").val()==""){
                $("#cpassword").addClass('is-invalid');
                return false;
            }else{
                $("#cpassword").removeClass('is-invalid');
            }
        });
    });

function checkOut(){
	
}
/*$("#save").on("click", function(e){
  e.preventDefault(); // prevent de default action, which is to submit
  // save your value where you want
  data.select = $("#exampleSelect").value();
  // or call the save function here
  // save();
  $(this).prev().click();

});*/
</script>
<?php
echo'</html>';
?>