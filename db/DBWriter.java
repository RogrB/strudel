package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import strudel.logic.Strudel;
import strudel.logic.StrudelComment;

public class DBWriter {
    
    public void writeStrudelToDB(Connection connection, Strudel strudel) {
        try {
            String sql = "insert into strudel (id,time,votes,height,color,message) "
                  + "values (DEFAULT, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, strudel.getTime());
            stmt.setInt(2, strudel.getVotes());
            stmt.setInt(3, strudel.getHeight());
            stmt.setString(4, strudel.getColor());
            stmt.setString(5, strudel.getMessage());
            stmt.executeUpdate();

        } catch(SQLException ex) {
            ex.printStackTrace();      
        }
    }
    
    public void writeCommentToDB(Connection connection, StrudelComment strudel) {
        try {
            String sql = "insert into comments (id,commentID,time,votes,height,color,message) "
                  + "values (?, DEFAULT, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, strudel.getID());
            stmt.setLong(2, strudel.getTime());
            stmt.setInt(3, strudel.getVotes());
            stmt.setInt(4, strudel.getHeight());
            stmt.setString(5, strudel.getColor());
            stmt.setString(6, strudel.getMessage());
            System.out.println(stmt);
            stmt.executeUpdate();

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
        String sql = "update comments set votes = votes-1 where commentID = '" + id + "';";
        setVote(connection, sql);
    }
    
    public void upVoteComment(Connection connection, int id) {
        String sql = "update comments set votes = votes+1 where commentID = '" + id + "';";
        setVote(connection, sql);
    }
    
}
