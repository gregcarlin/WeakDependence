package en.gregthegeek.gen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionReader {
    private final List<String> items = new ArrayList<String>();
    private final Random rand = new Random();
    
    public CollectionReader(String file) {
        this(new File(file));
    }
    
    public CollectionReader(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = br.readLine()) != null) {
                if(line.trim().length() <= 0) continue; // skip blank lines
                items.add(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getRandomLine() {
        int len = items.size();
        if(len <= 0) return null; // error reading file or empty file
        
        return items.get(rand.nextInt(len));
    }
}
