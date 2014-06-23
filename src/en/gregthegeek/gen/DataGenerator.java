package en.gregthegeek.gen;

import java.io.File;
import java.io.IOException;

import en.gregthegeek.util.BufferedWriter;
import en.gregthegeek.util.Random;

public class DataGenerator {
    private static final String DIR_BASE = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/";
    private static final String DIR_DATA = DIR_BASE + "data_sources/";
    private static final String DIR_OUT  = DIR_BASE + "queries/schemas_and_data/";
    
    public static void main(String[] args) {
        try {
            writeDepts();
            for(int i=2; i<=5; i++) {
                writeEmps((int) Math.pow(10, i));
            }
            
            for(int i=2; i<=5; i++) {
                writeParts((int) Math.pow(10, i));
            }
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
        BufferedWriter bw = new BufferedWriter(out);
        
        for(int i=0; i<amt; i++) {
            boolean isMale = rand.nextBoolean();
            String fName = (isMale ? male : female).getRandomLine(); // love this syntax
            String lname = last.getRandomLine();
            String gender = isMale ? "M" : "F";
            String pos = rand.choose("P", "E", "T");
            String dept = depts.getRandomLine();
            bw.writelnf("create permanent Emp(\"%s\" as fName, \"%s\" as lname, enum_gender.%s as sex, %s as birthday, %s as hire_date, %f as sal, enum_pos.%s as position, ref(Dept where dname = \"%s\") as works_in);", fName, lname, gender, rand.nextSBQLDate(), rand.nextSBQLDate(), rand.nextFloat(2000), pos, dept);
        }
        
        bw.close();
	}
    
    public static final void writeDepts() throws IOException {
        CollectionReader depts  = new CollectionReader(DIR_DATA + "department_names.txt");
        BufferedWriter bw = new BufferedWriter(DIR_OUT + "depts.txt");
        
        for(String line : depts.getAllLines()) {
            bw.writelnf("create permanent Dept(\"%s\" as dname);", line);
        }
        
        bw.close();
    }
    
    public static final void writeParts(int amt) throws IOException {
        final CollectionReader names = new CollectionReader(DIR_DATA + "last_names.txt");
        final String[] kinds = {"ram", "memory", "storage", "display", "keyboard", "mouse", "hard drive", "optical drive", "processor", "graphics card", "gpu", "cpu", "fan", "power supply"};
        
        BufferedWriter bw = new BufferedWriter(DIR_OUT + "parts-" + amt + ".txt");
        Random rand = new Random();
        
        for(int i=0; i<amt/10; i++) {
            for(int j=0; j<10; j++) {
                bw.writelnf("create permanent Part(\"%s\" as name, \"%s\" as kind, %f as detailCost, %f as detailMass, %f as assemblyCost, %f as assemblyMass);", names.getRandomLine(), rand.choose(kinds), rand.nextFloat(100), rand.nextFloat(100), rand.nextFloat(100), rand.nextFloat(100));
                if(i >= 1) {
                    bw.writelnf("Part[%d] :<< component(create permanent Component(%d as amount, ref(Part[%d]) as leadsTo))", rand.nextInt((i - 1) * 10, i * 10), rand.nextInt(10), i * 10 + j);
                }
            }
        }
        
        bw.close();
    }
}
