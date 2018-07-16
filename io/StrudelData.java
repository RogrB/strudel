package io;

public class StrudelData {
    
    private final int id;
    private final String vote;
    
    public StrudelData(int id, String vote) {
        this.id = id;
        this.vote = vote;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getVote() {
        return this.vote;
    }
    
}
