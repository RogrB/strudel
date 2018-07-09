package db;

import java.sql.*;

public class DBConnection {
   
    private Connection connection;

    public void init(){
    
       try{
        
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection(
                "jdbc:mysql://localhost/strudel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root",""
                );
        }
        catch(Exception e){
            System.out.println("Failed to get connection");
            e.printStackTrace();
        }
    }
    
    
    public Connection getConnection(){
        return connection;
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

      if(connection !=null){

           try{
                 connection.close();
              }
              catch(Exception e){}
      }
    }
}