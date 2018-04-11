<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
String error = (String)request.getAttribute("error");
if("error".equals(error))
{
	out.println("Please login to continue");
}
%>


<%if("true".equals(request.getAttribute("check")))
	{
		%> 
		<p>Successful - Login to continue</p>
	<% }%>
<div class="login-page2">
  <div class="form2">
  <h1>SIGN IN</h1>
  </div>
  </div>
<div class="login-page">
  <div class="form">
    <form class="login-form-cust" name = "loginUser" action = "LoginServlet" method = "post">
      <input type="text" placeholder="username" name = "email" required/>
      <input type="password" placeholder="password" name = "password" required/>
     Select Role : <br><br><input type = "radio" name = "role" id = "customer" value="customer"/>Customer &nbsp;&nbsp;
	<input type = "radio" name = "role" id = "manager" value="manager"/>Manager
		<br>
		<br>
		<input type = "hidden" value = "login" name = "action" style="background-color: #4CAF50">
		<input type ="submit" value = "Submit" style="background-color: #4CAF50">
      </form>
      <form name = "signUp" action = "LoginServlet" method = "post" >
<input type = "submit" value = "SignUp" style="background-color: #4CAF50">
<input type = "hidden" value = "signup" name = "action">
</form>
      
  </div>
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>



</body>
</html>