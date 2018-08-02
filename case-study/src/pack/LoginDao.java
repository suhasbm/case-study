package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class LoginDao {
	public static int validate(String name,String pass){  
		boolean status=false; 
		String day=null;
		int value=-1;
		try{  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521/xe","system","tiger");  
		      
		PreparedStatement ps=con.prepareStatement(
		"select * from usersinfo where username=? and password=?");  
		ps.setString(1,name);  
		ps.setString(2,pass);  
		      
		ResultSet rs=ps.executeQuery();  
		status=rs.next(); 
		
		if(status==true)
		{
					ps=con.prepareStatement(
					"select day from usersinfo where username=?");  
					ps.setString(1,name);  
					rs=ps.executeQuery();  
					while(rs.next())
					{
						day=rs.getString(1);
					}
					if(LocalDate.now().toString().equals(day))
					{
						System.out.println("Date match");
						value=2;
					}
					else
					{
						value=1;
					}
		}
		          
		}catch(Exception e){System.out.println(e);}  
		return value;  
		}  
	
	public static boolean getRole(String name)
	{
		boolean status=false;
		String role=null;
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@localhost:1521/xe","system","tiger");  
			      
			PreparedStatement ps;  
			      
			ResultSet rs;  
			
						ps=con.prepareStatement(
						"select role from usersinfo where username=?");  
						ps.setString(1,name);  
						rs=ps.executeQuery();  
						while(rs.next())
						{
							role=rs.getString(1);
						}
						if(role.equals("admin"))
						{
							System.out.println("admin");
							status=true;
						}
						else
						{
							status=false;
						}
			
			          
			}catch(Exception e){System.out.println(e);}  
		
		
		
		return status;
	}
	
	
	
	
}
