<%@page import="pojo.CustomerByFlight"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
<style>
#customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
</style>
</head>
<body>



<table id="customers">

<tr>
<th>FirstName</th>
<th>LastName</th>
<th>Reservation Number</th>
<th>TravelDate</th>
<th>Passengers</th>
</tr>
<%

ArrayList<CustomerByFlight> list = (ArrayList<CustomerByFlight>)session.getAttribute("custByFlight");
for(CustomerByFlight cbf : list)
{
	
	%>
	
	<tr>
	<td><%out.println(cbf.getFirstName()); %></td>
	<td><%out.println(cbf.getLastName()); %></td>
	<td><%out.println(cbf.getReservationNumber()); %></td>
	<td><%out.println(cbf.getTravelDate()); %></td>
	<td><%out.println(cbf.getNumberOfPassengers()); %></td>
	</tr>
	<%
	
	
}
%>


</table>

<div class="login-page2">
  <div class="form2">
  <br>
  <a href = "managerHome.jsp"><h2> Go To HomePage </h2> </a>
  <br>
   </div>
  </div>

</body>
</html>