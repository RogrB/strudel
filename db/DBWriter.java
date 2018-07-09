package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import strudel.logic.Strudel;

public class DBWriter {
    
    public void write(Connection connection, Strudel strudel) {
        
        try {
            Statement stmt = connection.createStatement();
        //$sql = "Insert INTO user (userID,username,email,password,joindate)";
        //$sql .= "Values (DEFAULT,'$user->username','$user->email','$hashpassord','$date');";
            String sql = "insert into strudel (id,time,votes,height,color,message) "
                  + "values (DEFAULT, '" + strudel.getTime() + "', '" + strudel.getVotes() +"', '"
                  + strudel.getHeight() +"', '" + strudel.getColor() + "', '" + strudel.getMessage() + "');";
            //int countInserted = stmt.executeUpdate(sql);
            stmt.executeUpdate(sql);
            //System.out.println(countInserted + " records inserted.\n");


        } catch(SQLException ex) {
           ex.printStackTrace();      
        }
    }
    
    public void upVote(Connection connection, int id) {
        try {
            Statement stmt = connection.createStatement();

            String sql = "update strudel set votes = votes+1 where id = '" + id + "';";
            stmt.executeUpdate(sql);

        } catch(SQLException ex) {
           ex.printStackTrace();      
        }            
    }

    public void downVote(Connection connection, int id) {
        try {
            Statement stmt = connection.createStatement();

            String sql = "update strudel set votes = votes-1 where id = '" + id + "';";
            stmt.executeUpdate(sql);

        } catch(SQLException ex) {
           ex.printStackTrace();      
        }            
    }
    
}
