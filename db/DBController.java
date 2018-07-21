package db;

import java.sql.Connection;
import java.util.ArrayList;
import strudel.logic.Strudel;
import strudel.logic.StrudelComment;

public class DBController {
    
    DBConnection connection = new DBConnection();
    DBReader reader = new DBReader();
    DBWriter writer = new DBWriter();
    Connection con;
    
    public DBController() {
        connection.init();
        con = connection.getConnection();
    }
    
    public void writeStrudelToDB(Strudel strudel) {
        writer.writeStrudelToDB(con, strudel);
    }
    
    public void writeCommentToDB(StrudelComment strudel) {
        writer.writeCommentToDB(con, strudel);
    }
    
    public ArrayList<Strudel> readStrudels() {
        ArrayList<Strudel> strudels = reader.readStrudels(con);     
        return strudels;
    }
    
    public int readVotes(int id) {
        return reader.readVotes(con, id);
    }
        
    public void upVote(int id) {
        writer.upVote(con, id);
    }
    
    public void downVote(int id) {
        writer.downVote(con, id);
    }
    
    public void downVoteComment(int id) {
        writer.downVoteComment(con, id);
    }
    
    public void upVoteComment(int id) {
        writer.upVoteComment(con, id);
    }
    
    public int countComments(int id) {
        return reader.countComments(con, id);
    }
    
    public ArrayList<Strudel> sortByComments() {
        return reader.sortByComments(con);
    }
    
    public ArrayList<Strudel> sortByNewest() {
        return reader.sortByNewest(con);
    }
    
    public ArrayList<Strudel> sortByVotes() {
        return reader.sortByVotes(con);
    }    
    
    public ArrayList<StrudelComment> getComments(int id) {
        return reader.getComments(con, id);
        
    }
    
}
