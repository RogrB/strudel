package db;

import java.sql.*;

public class DBConnection {
   
    private Connection myConnection;

    public void init(){
    
       try{
        
        Class.forName("com.mysql.jdbc.Driver");
        myConnection=DriverManager.getConnection(
                "jdbc:mysql://localhost/strudel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root",""
                );
        test(myConnection);
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }
    
    
    public Connection getMyConnection(){
        return myConnection;
    }
    
    
    public void close(ResultSet rs){
        
        if(rs !=null){
            try{
               rs.close();
            }
            catch(Exception e){}
        
        }
    }
    
     public void close(java.sql.Statement stmt){
        
        if(stmt !=null){
            try{
               stmt.close();
            }
            catch(Exception e){}
        
        }
    }
     
  public void destroy(){
  
    if(myConnection !=null){
    
         try{
               myConnection.close();
            }
            catch(Exception e){}
        
        
    }
  }
  
  public void test(Connection connection) {
      try {
         Statement stmt = connection.createStatement();
         String strSelect = "select * from strudel";
         System.out.println("The SQL query is: " + strSelect);
         System.out.println();
 
         ResultSet rset = stmt.executeQuery(strSelect);
 
         System.out.println("The records selected are:");
         int rowCount = 0;
         while(rset.next()) {
            int id = rset.getInt("id");
            long time = rset.getLong("time");
            int votes = rset.getInt("votes");
            int height = rset.getInt("height");
            int color = rset.getInt("color");
            String message = rset.getString("message");
            System.out.println(id + ", " + time + ", " + votes + ", " + height + ", " + color + ", " + message);
            ++rowCount;
         }
         System.out.println("Total number of records = " + rowCount);
 
      } catch(SQLException ex) {
         ex.printStackTrace();      
      }
  }
}