package en.gregthegeek.gen.query;

import static en.gregthegeek.gen.query.Constants.*;

import java.io.*;

public class QueryGen {
    private static final String DIR = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/queries/";
    
    public static void main(String[] args) throws IOException {
        
        Query add = new Query("%d + %d" , NUM_PAIRS);
        Query sub = new Query("%d - %d" , NUM_PAIRS);
        Query mul = new Query("%d * %d" , NUM_PAIRS);
        Query div = new Query("%d / %d" , NUM_PAIRS);
        Query mod = new Query("%d %% %d", NUM_PAIRS);
        Query neg = new Query("-%d"     , NUM_SINGS);
        
        Query and = new Query("%s and %s", LOGIC_PAIRS);
        Query or  = new Query("%s or %s" , LOGIC_PAIRS);
        Query not = new Query("not %s"   , LOGIC_SINGS);
        
        Query cat = new Query("%s + %s"  , STR_PAIRS);
        
        Query eqNum  = new Query("%d = %d" , NUM_PAIRS);
        Query eqStr  = new Query("%s = %s" , STR_PAIRS);
        Query neqNum = new Query("%d <> %d", NUM_PAIRS);
        Query neqStr = new Query("%s <> %s", STR_PAIRS);
        
        Query gr  = new Query("%d > %d" , NUM_PAIRS);
        Query ls  = new Query("%d < %d" , NUM_PAIRS);
        Query gre = new Query("%d >= %d", NUM_PAIRS);
        Query lse = new Query("%d <= %d", NUM_PAIRS);
        
        // TODO <str> ~~ <regex>
        // TODO <str> !~ <regex>
        // TODO <x> as <y>
        // TODO <x> groupas <y>
        
        Query now   = new Query("now()");
        Query dform = new Query("dateprec(1900-01-01, %s)", DATE_OPTS);
        
        Query sum = new Query("sum %s"  , STRUCTS);
        Query cnt = new Query("count %s", STRUCTS);
        Query min = new Query("min %s"  , STRUCTS);
        Query max = new Query("max %s"  , STRUCTS);
        Query avg = new Query("avg %s"  , STRUCTS);
        
        // TODO more
        
        final Query[]  queries = {  add,   sub,   mul,   div,   mod,   neg,   and,   or,   not,   cat,   eqNum,   eqStr,   neqNum,   neqStr,   gr,   ls,   gre,   lse,   now,   dform,   sum,   cnt,   min,   max,   avg};
        final String[] names   = {"add", "sub", "mul", "div", "mod", "neg", "and", "or", "not", "cat", "eqNum", "eqStr", "neqNum", "neqStr", "gr", "ls", "gre", "lse", "now", "dform", "sum", "cnt", "min", "max", "avg"};
        assert queries.length == names.length;
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(DIR + "all.txt"));
        for(int i=0; i<queries.length; i++) {
            String fileName = names[i] + ".txt";
            
            QueryWriter qw = new QueryWriter(new FileWriter(DIR + fileName), 10, names[i]);
            qw.write(queries[i]);
            qw.close();
            
            bw.write("batch " + fileName);
            bw.newLine();
        }
        bw.close();
    }
}
