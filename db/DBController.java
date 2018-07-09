package db;

import java.sql.Connection;
import java.util.ArrayList;
import strudel.logic.Strudel;

public class DBController {
    
    DBConnection connection = new DBConnection();
    DBRead reader = new DBRead();
    DBWriter writer = new DBWriter();
    Connection con;
    
    public DBController() {
        connection.init();
        con = connection.getConnection();
    }
    
    public void writeStrudel(Strudel strudel) {
        writer.write(con, strudel);
    }
    
    public ArrayList<Strudel> readStrudels() {
        ArrayList<Strudel> strudels = reader.readStrudels(con);     
        return strudels;
    }
    
}
