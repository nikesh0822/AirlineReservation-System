<%@page import="java.time.LocalDate"%>
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
<h3>Successfully booked.<br>Find flight Reservation Details below</h3>
<%
int totalFare = (int)session.getAttribute("totalFare");
String passengerNames[] = (String[])session.getAttribute("PassengerNames");

String flight = (String)session.getAttribute("flight");

if(flight.equals("round"))
{
	int[] ReservationNumbers = (int[])session.getAttribute("bookingRound");
	LocalDate date = (LocalDate)session.getAttribute("date");
	LocalDate date2 = (LocalDate)session.getAttribute("date2");
	String from = (String)session.getAttribute("from");
	String to = (String)session.getAttribute("to");
	
	
	%>
	
	Reservation Number : <%out.println(ReservationNumbers[0]); %><br><br>
	Date of Travel : <%out.println(date); %><br><br>
	From : <%out.println(from); %><br><br>
	To : <%out.println(to); %><br><br>
	
	
	
	Reservation Number :	<%out.println(ReservationNumbers[1]); %><br><br>
	Date of Travel : <%out.println(date2); %><br><br>
	From : <%out.println(to); %><br><br>
	To : <%out.println(from); %><br><br>
	
	
	<%
	
}

else 
	
{
	int ReservationNumber = (int)session.getAttribute("booking");
	LocalDate depart = (LocalDate)session.getAttribute("depart");
	String from = (String)session.getAttribute("from");
	String to = (String)session.getAttribute("to");
	%>
	
	Reservation Number : <%out.println(ReservationNumber); %><br><br>
	Date of Travel : <%out.println(depart); %><br><br>
	From : <%out.println(from); %><br><br>
	To : <%out.println(to); %><br><br>
	
	
	<%

}

%>


<h2>Passengers Traveling </h2>
<%

for(int i = 0 ; i < passengerNames.length ; i++)
{
	
	%>
	
	Passenger <%out.println(i+1);%> : <%out.println(passengerNames[i]); %> <br><br>
	<%
	
	
}
%>
<br>
Total Fare : <%out.println(totalFare);%> <br><br>




<form action = "CustomerServlet" method = "post">
<input type = "hidden" value = "GoToHome" name = "action">
<input type = "submit" value = "Go to HomePage" style="background-color: #4CAF50">
</form>
</div>
</div>
</body>
</html>
