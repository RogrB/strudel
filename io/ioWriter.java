package io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ioWriter {
    
    public void writeStrudelData(String filename, StrudelData strudelData) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename, true)));
        dos.writeInt(strudelData.getID());
        dos.writeUTF(strudelData.getVote());
        dos.close();
    }    

    public void writeKarma(String filename, int karma) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.writeInt(karma);
        dos.close();
    }
    
    /*
    public static void writeStrings(String filename, List<String> words) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.writeInt(words.size());
        for (String word : words)
            dos.writeUTF(word);
        dos.close();
    }    
    */
}
