package en.gregthegeek.tests;

import java.io.IOException;

import odra.jobc.JOBC;
import odra.jobc.JOBCException;
import odra.jobc.Result;

public class Tester {
    public static void main(String[] args) {
        JOBC db = new JOBC("admin", "admin", "localhost");
        try {
            db.connect();
            
            Result result = db.execute("2+3");
            System.out.println(result.getInteger());
            
            db.close();
        } catch (JOBCException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
