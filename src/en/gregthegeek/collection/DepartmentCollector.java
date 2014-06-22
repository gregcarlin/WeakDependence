package en.gregthegeek.collection;

import java.io.*;

public class DepartmentCollector {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FirstNameCollector.DIR + "departments.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter(FirstNameCollector.DIR + "department_names.txt"));
            
            String line;
            br.readLine(); // skip first line
            while((line = br.readLine()) != null) {
                bw.write(line.substring(line.indexOf(';') + 1));
                bw.newLine();
            }
            
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
