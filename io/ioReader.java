package io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ioReader {  
  
    public static List<String> readWords(String filename) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        int count = dis.readInt();
        List<String> words = new ArrayList<String>(count);
        while (words.size() < count)
            words.add(dis.readUTF());
        return words;
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
