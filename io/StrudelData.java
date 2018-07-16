package io;

public class StrudelData {
    
    private boolean hasVoted;
    private int id;
    private String vote;
    
    public StrudelData(int id, String vote) {
        this.id = id;
        this.vote = vote;
        hasVoted = true;
    }
    
    /*
    public void setHasVoted(boolean voted) {
        this.hasVoted = voted;
    }
    
    public void setID(int id) {
        this.id = id;
    }
    
    public void setVote(String vote) {
        this.vote = vote;
    }*/
    
    public boolean hasVoted() {
        return this.hasVoted;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getVote() {
        return this.vote;
    }
    
}
