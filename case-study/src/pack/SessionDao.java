package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SessionDao {
	public static boolean store(String username, String signInDate, String signInTime, String signOutTime, String dayAttendance)
	{
		boolean status=false;
		
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521/xe","system","tiger");  
			      
			PreparedStatement ps=con.prepareStatement(
			"insert into signinout values (?,?,?,?,?)");   
			ps.setString(1,username);  
			ps.setString(2,signInDate);
			ps.setString(3,signInTime);
			ps.setString(4,signOutTime);
			ps.setString(5,dayAttendance);
			      
			ResultSet rs=ps.executeQuery();  
			status=rs.next();  
			  
			 ps=con.prepareStatement(
			"update usersinfo set day=? where username=?");   
			 			ps.setString(1,signInDate);  
						ps.setString(2,username);  
						rs=ps.executeQuery();  
			
			}catch(Exception e){System.out.println(e);} 
		
		
		
		return status;
	}
}
