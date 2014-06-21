package en.gregthegeek.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import odra.jobc.JOBC;
import odra.jobc.JOBCException;
import odra.jobc.Result;
import odra.jobc.SBQLQuery;

public class Tester {
    private static final String DIR = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/queries/";
    
    public static void main(String[] args) {
        JOBC db = new JOBC("admin", "admin", "localhost");
        try {
            db.connect();
            /*db.execute("cd ..");
            db.execute("cd greg/queries/");*/
            //SBQLQuery query = db.getSBQLQuery("Person where lName=\"York\"");
            //Result result = db.execute(query);
            db.execute("set test plaintimes");
            
            File queryFolder = new File(DIR);
            assert queryFolder.isDirectory(); // hey, it's better than nothing
            for(File queryFile : queryFolder.listFiles()) {
                
                if(queryFile.isHidden()) continue; // frickin' DS_Store
                
                //db.execute("batch ../greg/queries/" + queryFile.getName());
                BufferedReader br = new BufferedReader(new FileReader(queryFile)); // fine, we'll do this ourselves
                String line;
                while((line = br.readLine()) != null) {
                    if(line.length() <= 1) continue;
                    db.execute(line);
                }
                br.close();
                break;
                
            }
            
            db.close();
        } catch (JOBCException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
