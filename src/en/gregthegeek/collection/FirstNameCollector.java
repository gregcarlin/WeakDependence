package en.gregthegeek.collection;

// using *, #yolo
import java.io.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class FirstNameCollector {
    protected static final String DIR = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/data_sources/";
    
    public static void main(String[] args) {
        try {
            File male = new File(DIR + "male_names.txt");
            File female = new File(DIR + "female_names.txt");
            male.createNewFile();
            female.createNewFile();
            BufferedWriter mw = new BufferedWriter(new FileWriter(male));
            BufferedWriter fw = new BufferedWriter(new FileWriter(female));
            
            Document doc = Jsoup.connect("http://www.babynamewizard.com/the-top-1000-baby-names-of-2011-united-states-of-america").get();
            Elements rows = doc.getElementsByClass("tableizer-table").get(0).getElementsByTag("tr");
            for(Element e : rows) {
                if(e.className().toLowerCase().equals("tableizer-firstrow")) continue; // skip header
                
                Elements cols = e.getElementsByTag("td");
                fw.write(cols.get(1).html());
                fw.newLine();
                mw.write(cols.get(6).html());
                mw.newLine();
            }
            
            mw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
