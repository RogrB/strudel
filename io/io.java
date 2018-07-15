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

public class io {  
  
    public static List<String> readWords(String filename) throws IOException {
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        int count = dis.readInt();
        List<String> words = new ArrayList<String>(count);
        while (words.size() < count)
            words.add(dis.readUTF());
        return words;
    }

    public static void writeStrings(String filename, List<String> words) throws IOException {
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));
        dos.writeInt(words.size());
        for (String word : words)
            dos.writeUTF(word);
        dos.close();
    }
    
    public boolean checkIfDataFileExists(String dataPath) {
        File dataFile = new File(dataPath);
        return dataFile.exists();
    }
  
  /*
  public static void main(String... aArgs) throws IOException{
    final static String FILE_NAME = "C:\\Temp\\cottage.jpg";
    final static String OUTPUT_FILE_NAME = "C:\\Temp\\cottage_output.jpg";    
    io binary = new io();
    byte[] bytes = binary.readSmallBinaryFile(FILE_NAME);
    log("Small - size of file read in:" + bytes.length);
    binary.writeSmallBinaryFile(bytes, OUTPUT_FILE_NAME);
  }*/  
  
}  
