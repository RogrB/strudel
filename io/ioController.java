
package io;

import java.io.IOException;

public class ioController {
    
    io io = new io();
    private int karma;
    private final String dataPath = "/data/StrudelKarma.data";
    
    public ioController() {
        init();
    }
    
    public void init() {
        if (io.checkIfDataFileExists(dataPath)) {
            
        }
        else {
            String writeTo = "Karma = 100";
            
        }
    }
    
    public int getKarma() {
        return this.karma;
    }
    
    public void setKarma(int karma) {
        this.karma = karma;
    }      
    
}
