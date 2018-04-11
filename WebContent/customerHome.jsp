<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
 <%@ page import = "java.time.LocalDate" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Home</title>
<link rel="stylesheet" href="css/style.css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>

<script src="jQuery_files/jquery-1.11.1.min.js"></script>

<script src="jQuery_files/jquery-ui.min.js"></script>

</head>
<body>
<script type="text/javascript">
        $(function () {
            $("#single_from").autocomplete({
            	
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "airportName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
            
            $("#single_to").autocomplete({
                source:function(request, response) {
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "airportName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
            
            $("#multiple_from").autocomplete({
            	
                source:function(request, response) {
                	
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "airportName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
            
            $("#multiple_to").autocomplete({
            	
                source:function(request, response) {
                	
                	console.log(request["term"]);
                    $.ajax({
                        url: "AjaxRequest",
                        type: "POST",
                        data: { term: request.term, type: "airportName" },
                        dataType: "json",
                        success: function( data) {
                            
                            response(data);
                        }
                    });
                }
            });
        })
  </script>
<div class="login-page2">
  <div class="form2">
  <h1>Home Page</h1>
  </div>
  </div>
 <div class="login-page">
  <div class="form">
  
  
  <% HttpSession session2 = request.getSession(false);
  if(session2.equals(null))
  {
	  request.setAttribute("error", "error");
	  System.out.println("check");
	  response.sendRedirect("login.jsp");
  }
  else
  {
	  
  %>
  
<form name = "custReservations" action = "CustomerServlet" method = "post" class="login-form-cust">
  
<input type = "submit" value = "Click here to get Current Reservation Details" style="background-color: #4CAF50">
<input type = "hidden" value = "CurrentReservationDetails" name = "action">
</form>
<!--  <br>
<form name = "custReservations" action = "CustomerServlet" method = "post" class="login-form-cust">
  
<input type = "submit" value = "Choose reservation number" style="background-color: #4CAF50">
<input type = "hidden" value = "ChooseReservations" name = "action">
</form>
 -->
<br>

<!-- new form created -->
<form name = "custReservations" action = "CustomerServlet" method = "post" class="login-form-cust">
  
<input type = "submit" value = "Click here to get all reservations" style="background-color: #4CAF50">
<input type = "hidden" value = "AllReservations" name = "action">
</form>

<br>
<!-- new form created -->
<form name = "custReservations" action = "CustomerServlet" method = "post" class="login-form-cust">
  
<input type = "submit" value = "Click here to get best seller flights" style="background-color: #4CAF50">
<input type = "hidden" value = "getBestSeller" name = "action">
</form>

<form name = "custChoice" action = "CustomerServlet" method = "post" class="login-form-cust">

Select type of booking : <br><br><input type = "radio" name = "trip" id = "single" value = "single" checked/>Single Trip &nbsp;&nbsp;&nbsp;&nbsp;
<input type = "radio" name = "trip" id = "round" value = "round" />Round Trip <br>
<!-- <input type = "radio" name = "trip" id = "multi" value = "multi"/>Multi-City Trip -->

<br>
<input type = "hidden" value = "tripChoice" name = "action">
<input type ="submit" value = "Submit" style="background-color: #4CAF50">

</form>
<br><br>

<%
String trip;
trip = (String)session.getAttribute("trips");
//out.println(trip);
if("single".equals(trip))
{ %>
  <form name = "custChoiceSingle" action = "CustomerServlet" method = "post">
  
  From : <input id="single_from" type = "text" name = "from" required/><br>
  To :  <input id="single_to" type = "text" name = "to" required/><br>
  Depart On :  <input type = "date" name = "date" id= "date" required/> <br>
  No of Passengers :  <input type = "number" name = "numberOfPassengers" required/><br>
  
  <br>
  <input type = "hidden" value = "singleChoices" name = "action">
  <input type ="submit" value = "Submit" style="background-color: #4CAF50">
  
  </form>
<% }
else if("round".equals(trip)){
%>
<form name = "custChoiceSingle" action = "CustomerServlet" method = "post">
  
  From : <input id="multiple_from" type = "text" name = "from" required/><br>
  To :  <input id="multiple_to" type = "text" name = "to" required/><br>
  Depart On :  <input type = "date" name = "date" id= "date" min="<%LocalDate.now();%>" required"/> <br>
  Return On :  <input type = "date" name = "date2" id= "date2" min="<%LocalDate.now();%>" required/> <br>
  No of Passengers :  <input type = "number" name = "numberOfPassengers" required/><br>
  
  
  <input type = "hidden" value = "roundChoices" name = "action">
  <input type ="submit" value = "Submit" style="background-color: #4CAF50">
  
  </form>
<% }%>
<br>
<form name = "logout" action = "LoginServlet" method = "post">
  
<input type = "submit" value = "Logout" style="background-color: red;">
<input type = "hidden" value = "logout" name = "action">
</form>

<%} %>
</div>
</div>
</body>
</html>