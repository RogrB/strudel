package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import strudel.logic.Strudel;

public class DBWriter {
    
    public void writeStrudelToDB(Connection connection, Strudel strudel) {
        try {
            Statement stmt = connection.createStatement();
            String sql = "insert into strudel (id,time,votes,height,color,message) "
                  + "values (DEFAULT, '" + strudel.getTime() + "', '" + strudel.getVotes() +"', '"
                  + strudel.getHeight() +"', '" + strudel.getColor() + "', '" + strudel.getMessage() + "');";
            stmt.executeUpdate(sql);

        } catch(SQLException ex) {
            ex.printStackTrace();      
        }
    }
    
    public void setVote(Connection connection, String sql) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);

        } catch(SQLException ex) {
            ex.printStackTrace();      
        }          
    }
    
    public void upVote(Connection connection, int id) {
        String sql = "update strudel set votes = votes+1 where id = '" + id + "';";
        setVote(connection, sql);
    }

    public void downVote(Connection connection, int id) {
        String sql = "update strudel set votes = votes-1 where id = '" + id + "';";          
        setVote(connection, sql);
    }
    
    public void downVoteComment(Connection connection, int id) {
        String sql = "update comments set vote = votes-1 where commentID = '" + id + "';";
        setVote(connection, sql);
    }
    
    public void upVoteComment(Connection connection, int id) {
        String sql = "update comments set vote = votes+1 where commentID = '" + id + "';";
        setVote(connection, sql);
    }
    
}
