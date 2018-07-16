package io;

import java.io.File;
import java.io.IOException;

public class ioController {
    
    ioReader reader = new ioReader();
    ioWriter writer = new ioWriter();
    private int karma;
    private final String dataPath = "/data/StrudelData.data";
    private final String karmaPath = "/data/StrudelKarma.data";
    
    public ioController() {
        initKarma();
    }
    
    public void initKarma() {
        try {
            if (reader.checkIfDataFileExists(karmaPath)) {
                this.karma = reader.readKarma(karmaPath);
            }
            else {
                this.karma = 100;
                File karmaFile = new File(karmaPath);
                karmaFile.createNewFile();
                writer.writeKarma(karmaPath, karma);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public int getKarma() {
        return this.karma;
    }
    
    public void setKarma(int karma) {
        this.karma = karma;
        writeKarma();
    }  
    
    public void writeKarma() {
        try {
            writer.writeKarma(karmaPath, karma);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
/*  USAGE
    // generate a million random words.
    List<String> words = new ArrayList<String>();
    for (int i = 0; i < 1000000; i++)
        words.add(Long.toHexString(System.nanoTime()));

    writeStrings("words", words);
    List<String> words2 = readWords("words");
    System.out.println("Words are the same is " + words.equals(words2));
*/