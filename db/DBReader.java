package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import strudel.logic.Strudel;
import strudel.logic.StrudelComment;


public class DBReader {
  
    public ArrayList<Strudel> getStrudelsFromDB(Connection connection, String sql) {
        ArrayList<Strudel> strudels = new ArrayList();
          try {
             Statement stmt = connection.createStatement();
             ResultSet rset = stmt.executeQuery(sql);

             while(rset.next()) {
                Strudel strudel = new Strudel();
                strudel.setID(rset.getInt("id"));
                strudel.setTime(rset.getLong("time"));
                strudel.setVotes(rset.getInt("votes"));
                strudel.setHeight(rset.getInt("height"));
                strudel.setColor(rset.getString("color"));
                strudel.setMessage(rset.getString("message"));
                strudels.add(strudel);
             }

          } catch(SQLException ex) {
             ex.printStackTrace();      
          }      
        return strudels;
    }
  
    public ArrayList<Strudel> readStrudels(Connection connection) {
        String sql = "select * from strudel order by time desc limit 50;";
        return getStrudelsFromDB(connection, sql);
  }

    public ArrayList<Strudel> sortByComments(Connection connection) {
        String sql = "SELECT strudel.*, COUNT(comments.id) AS amount\n" +
                     "FROM strudel LEFT JOIN comments ON strudel.id = comments.id\n" +
                     "GROUP BY strudel.id\n" +
                     "ORDER BY amount DESC limit 50";
        return getStrudelsFromDB(connection, sql);
    }
  
    public ArrayList<Strudel> sortByNewest(Connection connection) {
        String sql = "select * from strudel order by time desc limit 50;";
        return getStrudelsFromDB(connection, sql);
    }  

    public ArrayList<Strudel> sortByVotes(Connection connection) {
        String sql = "select * from strudel order by votes desc limit 50;";
        return getStrudelsFromDB(connection, sql);
    }  
  
    public ArrayList<StrudelComment> getComments(Connection connection, int id) {
        String sql = "select * from comments where id = '" + id + "' order by time desc;";
        ArrayList<StrudelComment> strudels = new ArrayList();
          try {
             Statement stmt = connection.createStatement();
             ResultSet rset = stmt.executeQuery(sql);

             while(rset.next()) {
                StrudelComment strudel = new StrudelComment();
                strudel.setID(rset.getInt("id"));
                strudel.setCommentID(rset.getInt("commentID"));
                strudel.setTime(rset.getLong("time"));
                strudel.setVotes(rset.getInt("votes"));
                strudel.setHeight(rset.getInt("height"));
                strudel.setColor(rset.getString("color"));
                strudel.setMessage(rset.getString("message"));
                strudels.add(strudel);
             }

          } catch(SQLException ex) {
             ex.printStackTrace();      
          }      
        return strudels;
    }      
  
    public int readVotes(Connection connection, int id) {
        int votes = 0;
          try {
             Statement stmt = connection.createStatement();
             String sql = "select votes from strudel where id = '" + id + "';";

             ResultSet rset = stmt.executeQuery(sql);
             votes = rset.getInt(sql);

          } catch(SQLException ex) {
             ex.printStackTrace();      
          }      
          return votes;
    }

    public int countComments(Connection connection, int id) {
        int comments = 0;
          try {
              Statement stmt = connection.createStatement();
              String sql = "select count(*) as amount from comments where id = '" + id + "';";           
              ResultSet rset = stmt.executeQuery(sql);
              if(rset.next()) {
                  comments = rset.getInt("amount");
              }

          } catch(SQLException ex) {
             ex.printStackTrace();      
          }          
        return comments;
    }

}
