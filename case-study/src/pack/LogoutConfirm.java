package pack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutConfirm
 */
@WebServlet("/LogoutConfirm")
public class LogoutConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        HttpSession session=request.getSession(false);
		
	    
	    String username=(String) session.getAttribute("username");  
	    String signInDate=(String)session.getAttribute("date");
	    String signInTime=(String)session.getAttribute("signInTime");
	    String signOutTime=(String)session.getAttribute("signOutTime");
	    String dayAttendance=(String)session.getAttribute("dayAttendance");
	    boolean status= SessionDao.store(username, signInDate, signInTime, signOutTime,dayAttendance);
        
        
        
        
        
        
        
        request.getRequestDispatcher("login.html").include(request, response);  
          
       // HttpSession session=request.getSession();  
        session.invalidate();  
         
        if(status==true)
        {
        	out.println("<p></p>");
        	out.print("Successfully stored in db"); 
        	out.println("<p></p>");
        }
        else
        {
        	out.print("failed to store in db"); 
        }
        out.print("You are successfully logged out!");  
          
        out.close();  
	}

}
