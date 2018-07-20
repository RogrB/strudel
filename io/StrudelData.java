package io;

public class StrudelData {
    
    private final int id;
    private final String vote;
    private int commentID = 0;
    
    public StrudelData(int id, String vote) {
        this.id = id;
        this.vote = vote;
    }
    
    public int getCommentID() {
        return this.commentID;
    }
    
    public void setCommentID(int id) {
        this.commentID = id;
    }
    
    public int getID() {
        return this.id;
    }
    
    public String getVote() {
        return this.vote;
    }
    
}
