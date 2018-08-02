<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Employee Details</h1>
<%
out.print("<form method=\"get\" action=\"/case-study/Logout\">\r\n" + 
		"    <button type=\"submit\">Sign Out</button>\r\n");
out.println("<button type=\"button\" disabled>Sign IN</button>");
out.println("</form>");

%>
<p></p>
<br>
<table border="1">
<tr>
	<th>Username</th>
	<th>Day</th>
	<th>SignIN</th>
	<th>SignOut</th>
	<th>Attended</th>
</tr>


<%
try{  
	Class.forName("oracle.jdbc.driver.OracleDriver");  
	Connection con=DriverManager.getConnection(  
	"jdbc:oracle:thin:@localhost:1521/xe","system","tiger");  
	      
	PreparedStatement ps;  
	      
	ResultSet rs;  
	
				ps=con.prepareStatement(
				"SELECT * FROM signinout");  
				  
				rs=ps.executeQuery();  
				while(rs.next())
				{
					%>
					<tr>
					<td><%=rs.getString(1) %></td>
					<td><%=rs.getString(2) %></td>
					<td><%=rs.getString(3) %></td>
					<td><%=rs.getString(4) %></td>
					<td><%=rs.getString(5) %></td>
					
					
					</tr>
					<% 
					
					
				}
				
			
	
	          
	}catch(Exception e){System.out.println(e);}  




%>
 </table>
</body>
</html>