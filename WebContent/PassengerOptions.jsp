<%@page import="pojo.FDetailsDisplay"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
 <div class="login-page">
  <div class="form">
Enter Passenger Details:
<form name = "PassengerInfo" action = "CustomerServlet" method = "post">
<table>
<tr>
<th></th>
<th>Name</th>
<% boolean isDomestic = (boolean)session.getAttribute("isDomestic");
if(!isDomestic){%>
<th>Passport Number</th>
<%} %>
</tr>
<%
int numberOfPassengers = (int)session.getAttribute("numberOfPassengers");
//int numberOfPassengers = 5;
for(int i = 0 ; i < numberOfPassengers ; i++)
{
	%>
	<tr>
	<td>Passenger <%out.println(i+1);%></td>
	<td><input type = "text" name = "Passenger<%out.print(i);%>" required></td>
	<%if(!(boolean)session.getAttribute("isDomestic")) {%>
	<td><input type = "text" name = "PassengerPassport<%out.print(i);%>" required></td>
	</tr>
	<%} %>
	<%
}
%>
</table>

<input type = "hidden" value = "PassengerInfo" name = "action">
<input type = "submit" value = "Proceed" style="background-color: #4CAF50">
</form>
</div>
</div>
</body>
</html>