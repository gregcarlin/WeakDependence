package en.gregthegeek.collection;

import java.io.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LastNameCollector {
    public static void main(String[] args) {
        try {
            File names = new File(FirstNameCollector.DIR + "last_names.txt");
            names.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(names));
            
            Document doc = Jsoup.connect("http://names.mongabay.com/data/1000.html").get();
            Elements rows = doc.getElementsByClass("boldtable").first().getElementsByTag("tr");
            boolean skipped = false;
            for(Element e : rows) {
                if(!skipped) { // skip first row
                    skipped = true;
                    continue;
                }
                
                String name = fix(e.getElementsByTag("td").first().html());
                bw.write(name);
                bw.newLine();
            }
            
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static final String fix(String name) {
        return Character.toUpperCase(name.charAt(0)) + name.toLowerCase().substring(1, name.length());
    }
}
