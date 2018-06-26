
package strudel;

import java.util.Date;


public class Strudel {
    
    private int id;
    private Date time;
    private int votes;
    private int height;
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    public void setVotes(int vote) {
        this.votes = vote;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getID() {
        return this.id;
    }
    
    public Date getTime() {
        return this.time;
    }
    
    public int getVotes() {
        return this.votes;
    }
    
    public int getHeight() {
        return this.height;
    }
    
}

