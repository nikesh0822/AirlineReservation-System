<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<%
//HttpSession ses2 = request.getSession(false);
//System.out.println(ses2 + "hello");
boolean addFlag = (boolean)request.getAttribute("addFlag");
System.out.println(addFlag);
if(addFlag)
{
	%>
	<div class="login-page2">
  <div class="form2">
  <h1>SUCCESSFUL</h1>
   </div>
  </div>
  <div class="login-page2">
  <div class="form2">
  <br>
  <a href = "managerHome.jsp"><h2> Go To HomePage </h2> </a>
  <br>
   </div>
  </div>
 	
 
	
	<%
}
%>


</body>
</html>