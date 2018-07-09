package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import strudel.logic.Strudel;


public class DBRead {
    
  public ArrayList<Strudel> readStrudels(Connection connection) {
        ArrayList<Strudel> strudels = new ArrayList();
        try {
           Statement stmt = connection.createStatement();
           String sql = "select * from strudel";

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
    
}
