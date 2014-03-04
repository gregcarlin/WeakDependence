package en.gregthegeek.gen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class DataGenerator {
    //private static final String DIR = "/Users/greg/Documents/School/Science_Research/ODRA_enums_mbleja/greg/";
	private static final String DIR = "E:\\\\School\\Science_Research\\ODRA_enums_mbleja\\greg\\";
    private static final String DIR_DATA = DIR + "data_sources/";
	
    public static void main(String[] args) {
	    File out = new File(DIR + "sample_data.txt");
	    CollectionReader male = new CollectionReader(DIR_DATA + "male_names.txt");
	    CollectionReader female = new CollectionReader(DIR_DATA + "female_names.txt");
	    Random rand = new Random();
	    try {
	        BufferedWriter bw = new BufferedWriter(new FileWriter(out));
	        
	        bw.write("create permanent Dept(\"test\" as dname)");
	        bw.newLine();
	        
	        for(int i=0; i<100; i++) {
	            boolean isMale = rand.nextBoolean();
	            String fName = (isMale ? male : female).getRandomLine(); // love this syntax
	            String lname = "Smith";
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
	            bw.write(String.format("create permanent Emp(\"%s\" as fName, \"%s\" as lname, enum_gender.%s as sex, %s as birthday, %s as hire_date, %f as sal, enum_pos.%s as position, ref(Dept where dname = \"test\") as works_in);", fName, lname, gender, getRandDate(rand), getRandDate(rand), rand.nextFloat() * 2000, pos));
	            bw.newLine();
	        }
	        
	        bw.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
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
