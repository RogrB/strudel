package strudel.logic;

import java.util.Date;
import java.util.ArrayList;
import java.util.Random;
import db.DBController;

public class StrudelLogic {
    
    private ArrayList<Strudel> strudels;
    
    private static final StrudelLogic instance = new StrudelLogic();
    private DBController db = new DBController();
    
    private StrudelLogic(){ strudels = db.readStrudels(); }

    public static StrudelLogic getInstance(){
        return instance;
    }    
    
    public ArrayList<Strudel> getStrudels() {
        return this.strudels;
    }
    
    public String getTime(Strudel s) {
        Date now = new Date();
        long dif = (now.getTime() - s.getTime()) / 1000;
        if (dif < 60) {
            return Long.toString(dif) + "s";
        }
        else if (dif >= 60 && dif < 3600) {
            return Long.toString(dif/60) + "min";
        }
        else if (dif >= 3600 && dif < 86400) {
            return Long.toString(dif/3600) + "h";
        }
        else {
            return Long.toString(dif/86400) + "d";
        }
    }
    
    public void upVote(int id) {
        db.upVote(id);
    }
    
    public void downVote(int id) {
        db.downVote(id);
    }
    
    public String getRandomColor() {
        String color;
        Random rand = new Random();
        int random = rand.nextInt((5 - 1)+1) + 1;
        switch(random) {
            case 1:
                color = "242424";
                break;
            case 2:
                color = "353535";
                break;
            case 3:
                color = "454545";
                break;
            case 4:
                color = "121212";
                break;
            case 5:
                color = "202020";
                break;                
            default:
                color = "FFFFFF";
                break;
        }
        return color;
    }
    
    public void post(String post) {
        Strudel s = new Strudel();
        Date now = new Date();
        s.setColor("#852342");
        s.setTime(now.getTime());
        s.setMessage(post);
        s.setVotes(0);
        s.setHeight(100);
        db.writeStrudel(s);
    }
    
    public ArrayList<Strudel> readStrudels() {
        strudels = db.readStrudels();
        return strudels;
    }
    
    public int countComments(int id) {
        return db.countComments(id);
    }
    
    public void sortNew() {
        strudels = db.sortNew();
    }
    
    public void sortComments() {
        strudels = db.sortComments();
    }
    
    public void sortVotes() {
        strudels = db.sortVotes();
    }
    
}
