<%@page import="pojo.RevenueSummary"%>
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
<th>No Of Tickets</th>
<th>Revenue</th>
<th>Month</th>
</tr>
<%

ArrayList<RevenueSummary> list = (ArrayList<RevenueSummary>)session.getAttribute("revenueByDestination");
for(RevenueSummary rs : list)
{
	
	%>
	
	<tr>
	<td><%out.println(rs.getNumberOfTickets()); %></td>
	<td><%out.println(rs.getRevenue()); %></td>
	<td><%out.println(rs.getMonth()); %></td>
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