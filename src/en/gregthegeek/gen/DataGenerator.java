package en.gregthegeek.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

import en.gregthegeek.util.BufferedWriter;

public class DataGenerator {
    private static final String DIR_BASE = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/";
    private static final String DIR_DATA = DIR_BASE + "data_sources/";
    private static final String DIR_OUT  = DIR_BASE + "queries/schemas_and_data/";
    
    public static void main(String[] args) {
        try {
            writeDepts();
            writeEmps(100);
            writeEmps(1000);
            writeEmps(10000);
            writeEmps(100000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
    public static final void writeEmps(int amt) throws IOException {
	    File out = new File(DIR_OUT + "emps-" + amt + ".txt");
	    
	    CollectionReader male   = new CollectionReader(DIR_DATA + "male_names.txt");
	    CollectionReader female = new CollectionReader(DIR_DATA + "female_names.txt");
	    CollectionReader last   = new CollectionReader(DIR_DATA + "last_names.txt");
	    CollectionReader depts  = new CollectionReader(DIR_DATA + "department_names.txt");
	    
	    Random rand = new Random();
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));
        
        for(int i=0; i<amt; i++) {
            boolean isMale = rand.nextBoolean();
            String fName = (isMale ? male : female).getRandomLine(); // love this syntax
            String lname = last.getRandomLine();
            String gender = isMale ? "M" : "F";
            String pos = null;
            switch(rand.nextInt(3)) {
            default:
            	assert false;
            	break;
            case 0:
            	pos = "P";
            	break;
            case 1:
            	pos = "E";
            	break;
            case 2:
            	pos = "T";
            	break;
            }
            assert pos != null;
            String dept = depts.getRandomLine();
            bw.write(String.format("create permanent Emp(\"%s\" as fName, \"%s\" as lname, enum_gender.%s as sex, %s as birthday, %s as hire_date, %f as sal, enum_pos.%s as position, ref(Dept where dname = \"%s\") as works_in);", fName, lname, gender, getRandDate(rand), getRandDate(rand), rand.nextFloat() * 2000, pos, dept));
            bw.newLine();
        }
        
        bw.close();
	}
    
    public static final void writeDepts() throws IOException {
        CollectionReader depts  = new CollectionReader(DIR_DATA + "department_names.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(DIR_OUT + "depts.txt"));
        
        for(String line : depts.getAllLines()) {
            bw.write(String.format("create permanent Dept(\"%s\" as dname);", line));
            bw.newLine();
        }
        
        bw.close();
    }
    
    private static final String getRandDate(Random rand) {
    	int year = rand.nextInt(40) + 1970;
        int day = rand.nextInt(365) + 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, day);
        return String.format("%d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }
}
