package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	          
	    String username=request.getParameter("username");  
	    String p=request.getParameter("userpass");  
	     int value= LoginDao.validate(username, p);
	    if(value==1){  
	    	
	    	String signInTime=LocalTime.now().toString();
	    	String signInDate=LocalDate.now().toString();
	    	
	    	HttpSession session=request.getSession();
	    	session.setAttribute("username", username);
	    	session.setAttribute("signInTime", signInTime);
	    	session.setAttribute("date", signInDate);
	    	
	    	
	    	out.print("<h1>Welcome "+username+"</h1>");
		    out.print("<hr><p></p>");
		    boolean role=LoginDao.getRole(username);
		    if(role)
		    {
		    	out.print("<h5><a href='employeeDetails.jsp'>Employee Details</a> </h5>");
		    	
		    }
		    out.println("<p></p><hr>");
		    out.print("<h1>Login in time: "+signInTime+"</h1><p></p>");
		    out.print("<h1>Today's Date is "+signInDate+"</h1>");
		    out.print("<hr><p></p>");
		    out.print("<form method=\"get\" action=\"/case-study/Logout\">\r\n" + 
		    		"    <button type=\"submit\">Sign Out</button>\r\n");
		    out.println("<button type=\"button\" disabled>Sign IN</button>");
		    out.println("</form>");
	  	
	    } 
	    else if(value==2){
	    	 out.print("You have logged in and out for the day");  
		        RequestDispatcher rd=request.getRequestDispatcher("login.html");  
		        rd.include(request,response);  
	    }
	    else{  
	        out.print("Sorry username or password error");  
	        RequestDispatcher rd=request.getRequestDispatcher("login.html");  
	        rd.include(request,response);  
	    }  
	          
	    out.close();  
	    } 
}


