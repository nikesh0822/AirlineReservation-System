<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Signup</title>
<link rel="stylesheet" href="css/style2.css">
</head>
<body>
<script>
function validateForm() {
    var x = document.forms["signupUser"]["email"].value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
    var ph = document.forms["signupUser"]["tel"].value;
    var phoneno = /^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$/;
    if(ph.match(phoneno))
          {
        return true;
          }
        else
          {
          alert("Invalid Phone Number");
          return false;
          }
}
</script>
<div class="login-page2">
  <div class="form2">
  <h1>SIGN UP</h1>
  </div>
  </div>
<div class="login-page">
  <div class="form">
    <form name = "signupUser" action = "LoginServlet" method = "post" class="login-form-cust" onsubmit="return validateForm();">
    
    Firstname <input type = "text" name = "firstname" placeholder="John" required/><br><br>
	Lastname  <input type = "text" name = "lastname" placeholder="Smith"required /><br><br>
	Address <input type = "text" name = "address" required /><br><br>
	City <input type = "text" name = "city" required /><br><br>
	StateLine <input type = "text" name = "stateLine" required /><br><br>
	Zipcode <input type = "text" name = "zipcode" required /><br><br>
	Telephone <input type = "text" name = "tel" placeholder="(XXX)-XXX-XXXX" required /><br><br>
	Credit Card Number <input type = "text" name = "creditCardNumber" required /><br><br>
	Email <input type = "email" name = "email" placeholder="xyz@email.com" required /><br><br>
	Password <input type = "password" name = "password" required /><br><br>
	
	<input type = "hidden" value = "actual_signup" name = "action" style="background-color: #4CAF50">
	<input type ="submit" value = "Submit" style="background-color: #4CAF50">
      
    </form>
      
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>




</body>
</html>