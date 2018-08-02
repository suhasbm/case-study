package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginValidator {
	   // JDBC driver name and database URL
	  // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/xe";
	   String res=null;
	   //  Database credentials
	   static final String USER = "system";
	   static final String PASS = "tiger";
	   
	   public String checkLogin(String username, String password) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("oracle.jdbc.OracleDriver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to a selected database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);
	      System.out.println("Connected database successfully...");
	      
	      //STEP 4: Execute a query
	      System.out.println("Inserting records into the table...");
	      stmt = conn.createStatement();
	      
	      String sql = "select * from usersinfo";
	      ResultSet rs=stmt.executeQuery(sql);
	      
	      while(rs.next())
	      {
	    	 if(username.equals(rs.getString(1))&& password.equals(rs.getString(2)))
	    	 {
	    		 res="true";
	    		 break;
	    	 }
	    	 else
	    	 {
	    		 res=null;
	    	 }
	      }
	      System.out.println("Inserted records into the table...");
	      
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            conn.close();
	      }catch(SQLException se){
	      }// do nothing
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	   return "false";
	}//end main
	}//end JDBCExample
