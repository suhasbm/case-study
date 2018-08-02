package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession(false);
			response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();
		    
		    //String username=(String) session.getAttribute("username");
		    String signInTime=(String)session.getAttribute("signInTime");
		  //  String signInDate=(String)session.getAttribute("date");
		    
		    String signOutTime=LocalTime.now().toString();
		    String dayAttendance="1.0 Day";
		    try {
		    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		    Date date1 = format.parse(signInTime);
		    Date date2 = format.parse(signOutTime);
		    long difference = date2.getTime() - date1.getTime();
		    if(difference<60000)//given for 1min change to 9hrs when needed
		    {
		    	out.println("<h2><font color=\"red\">Warning not completed 9 hrs--- Half day would be considered</font></h2>");
		    	dayAttendance="0.5 day";
		    }
		    session.setAttribute("signOutTime", signOutTime);
		    session.setAttribute("dayAttendance", dayAttendance);
		    out.print("<p></p>");
		    out.print("<p></p>");
		    out.print("<p></p>");
		    out.print("<h4>Are you sure?</h4>");
		    out.print("<p></p>");
		    out.print("<form method=\"get\" action=\"/case-study/LogoutConfirm\">\r\n" + 
		    		"    <button type=\"submit\">Confirm</button>\r\n");
		    out.println("</form>");
		   
		    
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    
	}

}
