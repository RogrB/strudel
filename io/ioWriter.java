package io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ioWriter {
    
    public static void writeStrings(String filename, List<String> words) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.writeInt(words.size());
        for (String word : words)
            dos.writeUTF(word);
        dos.close();
    }

    public void writeKarma(String filename, int karma) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.writeInt(karma);
        dos.close();
    }    
    
}
