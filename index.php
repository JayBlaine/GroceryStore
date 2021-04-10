<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
</head>
<body>
<!--Top of website with header-->
<div class = "jumbotron tetx-center" style="margin-bottom:0">
	<h1>Grocery Store</h1>
</div>
<!--Navbar for logging in/ or changing which items to view-->
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<a class="navbar-brand" href="index.php">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">	
			<button class="btn text-muted" id="login" type="button"  data-toggle="modal" data-target="#loginModal">Login</button>
			<button class="btn text-muted" id="signup" type="button" data-toggle="modal" data-target="#signupModal">Signup</button>
			<li class="nav-item">
				<a class="nav-link" href="Account.php">Account</a>
			</li>
	</div>
</nav>

<!--Put modals here-->
 <!-- The login Modal -->
 <form id="loginform" method="post" action="">
 <div class="container mt-3">
  <div class="modal fade" id="loginModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Login</h4>
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
          <div class="form-group">
			<label for="emailInput">Email address</label>
			<input type="email" class="form-control" id="emailInput" placeholder="Enter email">
		  </div>
		  <div class="form-group">
			<label for="passInput">Password</label>
			<input type="password" class="form-control" id="passInput" placeholder="Enter password">
		  </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
		<div class="form-group">
                <input type="submit" name="submit" value="Login" class="btn btn-block btn-danger">
         </div>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		 <!-- <button id="login1" onclick="login1()" class="btn btn-width bkgrnd-cyan save-details" type="button" name="save-details">Login</button>-->
        </div>
        
      </div>
    </div>
  </div>
 </div>
 </form>
 
 <!--The signup modal-->
<form method="post" id="singnupFrom" onSubmit="return validation();" action="">
 <div class="container mt-3">
  <div class="modal fade" id="signupModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">SignUp</h4>
          <button type="button" class="close" data-dismiss="modal">×</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <div class="form-group">
                <label class="font-weight-bold">Email</label>
                    <div class="input-group">
                        <input type="email" name="email" id="email" class="form-control" placeholder="Enter valid email">
                            <div class="input-group-append"><button type="button" class="btn btn-primary" onClick="return emailCheck();"><i class="fa fa-envelope"></i></button></div>
                    </div>
            </div>
            
                <div class="form-group">
                    <label class="font-weight-bold">User Name</label>
                     <input type="text" name="username" id="username" class="form-control" placeholder="First Last">
                </div>
                <div class="form-group">
                            <label class="font-weight-bold">Phone #</label>
                            <input type="text" name="phone" id="phone" class="form-control" placeholder="(000)-(0000000)">
                </div>
				<div class="form-group">
					<label class="font-weight-bold">Address</label>
					<input type="text" name="address" id="address" class="form-control" placeholder="Street address | City | State | Zip">
				</div>
                <div class="form-group">
                    <label class="font-weight-bold">Password</label>
                    <input type="password" name="password" id="password" class="form-control" placeholder="***********">
                </div>
                <div class="form-group">
                    <label class="font-weight-bold">Confirm Password</label>
                    <input type="password" name="cpassword" id="cpassword" class="form-control" placeholder="***********">
                        <em id="cp"></em>
                </div>
                <div class="form-group">
                    <label><input type="checkbox" name="condition" id="condition"> I agree with the <a href="javascript:;">Terms & Conditions</a> for Registration.</label>
                </div>
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
		 <div class="form-group">
                <input type="submit" name="submit" value="Sign Up" class="btn btn-block btn-danger">
         </div>
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
		  <!--<button id="save" onclick="save()" class="btn btn-width bkgrnd-cyan save-details" type="button" name="save-details">Signup</button>-->
        </div>
        
      </div>
    </div>
  </div>
 </div>
 <form>
 
<!--Start to  list avaliable items for sale-->
<div class="container" style="margin-top:30px">
	<div class="row">
		<div class="col-sm-4">
			<h2>Apples</h2>
			<div class="fakeimg">Temp Image</div>
			<p>Some facts or info about item</p>
			<div class="col-sm-8">
                <div class="input-group">
                    <span class="input-group-btn">
                    <button type="button" class="quantity-left-minus btn btn-danger btn-number"  data-type="minus" data-field="">
                        <span class="glyphicon glyphicon-minus"></span>
                    </button>
                    </span>
                    <input type="text" id="quantity" name="quantity" class="form-control input-number" value="10" min="1" max="100">
                        <span class="input-group-btn">
                        <button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus" data-field="">
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
						<button type="button" class="btn btn-primary" id="save2">Add To Cart</button>
                        </span>
                </div>
            </div>
			<br>
			<div class="col-sm-14">
			<h2>Bananas</h2>
			<div class="fakeimg">Temp Image</div>
			<p>Some fact or info about this product</p>
			<div class="col-sm-14">
                <div class="input-group">
                    <span class="input-group-btn">
                    <button type="button" class="quantity-left-minus btn btn-danger btn-number"  data-type="minus" data-field="">
                        <span class="glyphicon glyphicon-minus"></span>
                    </button>
                    </span>
                    <input type="text" id="quantity1" name="quantity" class="form-control input-number" value="10" min="1" max="100">
                        <span class="input-group-btn">
                        <button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus" data-field="">
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
						<button type="button" class="btn btn-primary" id="save2">Add To Cart</button>
                        </span>
                </div>
            </div>
			</div>
			<br>
			<div class="col-sm-14">
			<h2>Oranges</h2>
			<div class="fakeimg">Temp Image</div>
			<p>Some fact or info about this product</p>
			<div class="col-sm-14">
                <div class="input-group">
                    <span class="input-group-btn">
                    <button type="button" class="quantity-left-minus btn btn-danger btn-number"  data-type="minus" data-field="">
                        <span class="glyphicon glyphicon-minus"></span>
                    </button>
                    </span>
                    <input type="text" id="quantity2" name="quantity" class="form-control input-number" value="10" min="1" max="100">
                        <span class="input-group-btn">
                        <button type="button" class="quantity-right-plus btn btn-success btn-number" data-type="plus" data-field="">
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
						<button type="button" class="btn btn-primary" id="save2">Add To Cart</button>
                        </span>
                </div>
            </div>
			</div>
			<br>	
		</div>
    </div>	
 </div>	
</body>
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


/*$("#save").on("click", function(e){
  e.preventDefault(); // prevent de default action, which is to submit
  // save your value where you want
  data.select = $("#exampleSelect").value();
  // or call the save function here
  // save();
  $(this).prev().click();

});*/
</script>
</html>