<%@page import="pojo.FDetailsDisplay"%>
<%@page import="java.util.ArrayList"%>
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
<form action = "CustomerServlet" method = "post">
<input type = "hidden" name = "action" value = "flightOptions">
<table>
<%String flight = (String)session.getAttribute("flight");
//flight = "direct";
ArrayList<FDetailsDisplay> list = (ArrayList<FDetailsDisplay>)session.getAttribute("list");
System.out.println(list);
System.out.println(flight);
if(flight.equals("direct"))
{
%>

<tr>
<th></th>
<th>Airline</th>
<th>Flight Number</th>
<th>Departure Time</th>
<th>Arrival Time</th>
<th>Total Fare</th>
</tr>

<%	
for(FDetailsDisplay fdd : list)
{
	System.out.println(fdd.toString());
	%>
	
	<tr>
	<td><input type = "radio" name = "flightSelection" value = <%out.println(fdd.getObjectId()); %>></td>
	<td><%out.println(fdd.getAirlineName1()); %></td>
	<td><%out.println(fdd.getFLightNumber1()); %></td>
	<td><%out.println(fdd.getDepartureTime()); %></td>
	<td><%out.println(fdd.getArrivalTime()); %>
	<td><%out.println(fdd.getFare()); %></td>
	</tr>
	
	<%
}

}
else 
{
	if(flight.equals("one"))
	{
		%>

		<tr>
		<th></th>
		<th>Airline</th>
		<th>Flight Number</th>
		<th>Departure Time</th>
		<th>Arrival Time</th>
		<th>Total Fare</th>
		</tr>

		<%	
		for(FDetailsDisplay fdd : list)
		{
			%>
			
			<tr>
			<td><input type = "radio" name = "flightSelection" value = <%out.println(fdd.getObjectId()); %>></td>
			<td><%out.println(fdd.getAirlineName1()); %></td>
			<td><%out.println(fdd.getFLightNumber1()); %></td>
			<td><%out.println(fdd.getDepartureTime()); %></td>
			<td><%out.println(fdd.getArrivalTime()); %></td>
			<td colspan="2"><%out.println(fdd.getFare()); %></td></tr>
			<tr>
			<td></td>
			<td><%out.println(fdd.getAirlineName2()); %></td>
			<td><%out.println(fdd.getFLightNumber2()); %></td>
			<td><%out.println(fdd.getSecDepartureTime()); %></td>
			<td><%out.println(fdd.getSecArrivalTime()); %>
			</tr>
			
			<%
		}

	}
	else
	{
		if(flight.equals("two"))
		{
			%>
			<tr>
			<th></th>
			<th>Airline</th>
			<th>Flight Number</th>
			<th>Departure Time</th>
			<th>Arrival Time</th>
			<th>Total Fare</th>
			</tr>
			
			<%
			
			for(FDetailsDisplay fdd : list)
			{
				%>
				
				<tr>
				<td><input type = "radio" name = "flightSelection" value = <%out.println(fdd.getObjectId()); %>></td>
				<td><%out.println(fdd.getAirlineName1()); %></td>
				<td><%out.println(fdd.getFLightNumber1()); %></td>
				<td><%out.println(fdd.getDepartureTime()); %></td>
				<td><%out.println(fdd.getArrivalTime()); %></td>
				<td colspan="3"><%out.println(fdd.getFare()); %></td></tr>
				<tr>
				<td></td>
				<td><%out.println(fdd.getAirlineName2()); %></td>
				<td><%out.println(fdd.getFLightNumber2()); %></td>
				<td><%out.println(fdd.getSecDepartureTime()); %></td>
				<td><%out.println(fdd.getSecArrivalTime()); %>
				</tr>
				<tr>
				<td></td>
				<td><%out.println(fdd.getAirlineName3()); %></td>
				<td><%out.println(fdd.getFLightNumber3()); %></td>
				<td><%out.println(fdd.getThirdDepartureTime()); %></td>
				<td><%out.println(fdd.getThirdArrivalTime()); %>
				</tr>
				
				<%
			}
		}
		
		else
		{
			if(flight.equals("round"))
			{
				%>
				<tr>
				<th></th>
				<th>Airline</th>
				<th>Flight Number</th>
				<th>Departure Time</th>
				<th>Arrival Time</th>
				<th>Total Fare</th>
				</tr>
				
				<%
				
				for(FDetailsDisplay fdd : list)
				{
					%>
					
					<tr>
					<td><input type = "radio" name = "flightSelection" value = <%out.println(fdd.getObjectId()); %>></td>
					<td><%out.println(fdd.getAirlineName1()); %></td>
					<td><%out.println(fdd.getFLightNumber1()); %></td>
					<td><%out.println(fdd.getDepartureTime()); %></td>
					<td><%out.println(fdd.getArrivalTime()); %></td>
					<td colspan="2"><%out.println(fdd.getFare()); %></td></tr>
					<tr>
					<td></td>
					<td><%out.println(fdd.getAirlineName2()); %></td>
					<td><%out.println(fdd.getFLightNumber2()); %></td>
					<td><%out.println(fdd.getSecDepartureTime()); %></td>
					<td><%out.println(fdd.getSecArrivalTime()); %>
					</tr>
					
					
					<%
				}
			}
			else
			{
				if(flight.equals("differentDays"))
				{
					%>
					<tr><th rowspan = "7">No Flights available on selected Date</th></tr>
						<tr>
						<th></th>
						<th>Date</th>
						<th>Airline</th>
						<th>Flight Number</th>
						<th>Departure Time</th>
						<th>Arrival Time</th>
						<th>Total Fare</th>
						</tr>
						
						<%
						
						for(FDetailsDisplay fdd : list)
						{
							%>
							
							<tr>
							<td><input type = "radio" name = "flightSelection" value = <%out.println(fdd.getObjectId()); %>></td>
							<td><%out.println(fdd.getDateFlight()); %></td>
							<td><%out.println(fdd.getAirlineName1()); %></td>
							<td><%out.println(fdd.getFLightNumber1()); %></td>
							<td><%out.println(fdd.getDepartureTime()); %></td>
							<td><%out.println(fdd.getArrivalTime()); %>
							<td><%out.println(fdd.getFare()); %></td>
							</tr>
						
							<%
						}
				}
			}
		}
	}
}
%>

</table>
<input type = "submit" value = "Select Flight" style="background-color: #4CAF50">
</div>
</div>

</form>
</body>
</html>