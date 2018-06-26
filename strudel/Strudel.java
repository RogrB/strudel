
package strudel;

import java.util.Date;


public class Strudel {
    
    private int id;
    private long time;
    private int votes;
    private int height;
    private String color;
    private String message;
    private boolean voted;
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setTime(long time) {
        this.time = time;
    }
    
    public void setVotes(int vote) {
        this.votes = vote;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    public void setMessage(String strudel) {
        this.message = strudel;
    }
    
    public void setVoted(boolean voted) {
        this.voted = voted;
    }
    
    public int getID() {
        return this.id;
    }
    
    public long getTime() {
        return this.time;
    }
    
    public int getVotes() {
        return this.votes;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public boolean getVoted() {
        return this.voted;
    }
    
}

