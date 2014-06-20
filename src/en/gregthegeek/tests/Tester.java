package en.gregthegeek.tests;

import java.io.File;
import java.io.IOException;

import odra.jobc.JOBC;
import odra.jobc.JOBCException;

public class Tester {
    private static final String DIR = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/queries/";
    
    public static void main(String[] args) {
        JOBC db = new JOBC("admin", "admin", "localhost");
        try {
            db.connect();
            /*db.execute("cd ..");
            db.execute("cd greg/queries/");*/
            
            File queryFolder = new File(DIR);
            assert queryFolder.isDirectory(); // hey, it's better than nothing
            for(File queryFile : queryFolder.listFiles()) {
                
                if(queryFile.isHidden()) continue; // frickin' DS_Store
                
                db.execute("batch ../greg/queries/" + queryFile.getName());
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
