package io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ioReader {  
  
    public ArrayList<StrudelData> readStrudelData(String filename) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        ArrayList<StrudelData> data = new ArrayList<>();
        while (dis.available() > 0) {
            int id = dis.readInt();
            String vote = dis.readUTF();
            StrudelData strudel = new StrudelData(id, vote);
            data.add(strudel);
        }
        return data;
    }
    
    public int readKarma(String filename) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        return dis.readInt();
    }
    
    public boolean checkIfDataFileExists(String dataPath) {
        File dataFile = new File(dataPath);
        return dataFile.exists();
    }

}  
