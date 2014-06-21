package en.gregthegeek.gen.query;

import static en.gregthegeek.gen.query.Constants.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class QueryGen {
    private static final String DIR = "/Users/greg/Documents/School/Science_Research/workspace/ODRA_enums_mbleja/greg/queries/";
    
    private static final HashMap<String,Query> queries = new HashMap<String,Query>();
    
    public static void main(String[] args) throws IOException {
        
        Query add = new Query("%d + %d" , NUM_PAIRS);
        Query sub = new Query("%d - %d" , NUM_PAIRS);
        Query mul = new Query("%d * %d" , NUM_PAIRS);
        Query div = new Query("%d / %d" , NUM_PAIRS);
        Query mod = new Query("%d %% %d", NUM_PAIRS);
        Query neg = new Query("-%d"     , NUM_SINGS);
        
        p("add", add);
        p("sub", sub);
        p("mul", mul);
        p("div", div);
        p("mod", mod);
        p("neg", neg);
        
        Query and = new Query("%s and %s", LOGIC_PAIRS);
        Query or  = new Query("%s or %s" , LOGIC_PAIRS);
        Query not = new Query("not %s"   , LOGIC_SINGS);
        
        p("and", and);
        p("or", or);
        p("not", not);
        
        Query cat = new Query("%s + %s"  , STR_PAIRS);
        
        p("cat", cat);
        
        Query eqNum  = new Query("%d = %d" , NUM_PAIRS);
        Query eqStr  = new Query("%s = %s" , STR_PAIRS);
        Query neqNum = new Query("%d <> %d", NUM_PAIRS);
        Query neqStr = new Query("%s <> %s", STR_PAIRS);
        
        p("eqNum" , eqNum);
        p("eqStr" , eqStr);
        p("neqNum", neqNum);
        p("neqStr", neqStr);
        
        Query gr  = new Query("%d > %d" , NUM_PAIRS);
        Query ls  = new Query("%d < %d" , NUM_PAIRS);
        Query gre = new Query("%d >= %d", NUM_PAIRS);
        Query lse = new Query("%d <= %d", NUM_PAIRS);
        
        p("gr" , gr);
        p("ls" , ls);
        p("gre", gre);
        p("lse", lse);
        
        // TODO <str> ~~ <regex>
        // TODO <str> !~ <regex>
        // TODO <x> as <y>
        // TODO <x> groupas <y>
        
        Query now   = new Query("now()"); // TODO check runtime based on time of day
        Query dform = new Query("dateprec(1900-01-01, %s)", DATE_OPTS);
        
        p("now", now);
        p("dform", dform);
        
        Query sum = new Query("sum %s"  , STRUCT_SINGS);
        Query cnt = new Query("count %s", STRUCT_SINGS);
        Query min = new Query("min %s"  , STRUCT_SINGS);
        Query max = new Query("max %s"  , STRUCT_SINGS);
        Query avg = new Query("avg %s"  , STRUCT_SINGS);
        
        p("sum", sum);
        p("cnt", cnt);
        p("min", min);
        p("max", max);
        p("avg", avg);
        
        Query bag    = new Query("bag(%s)"    , STRUCT_SINGS);
        Query union  = new Query("%s union %s", STRUCT_PAIRS);
        Query struct = new Query("struct(%s)" , STRUCT_SINGS);
        Query comma  = new Query("%s"         , STRUCT_SINGS);
        
        p("bag"   , bag);
        p("union" , union);
        p("struct", struct);
        p("comma" , comma);
        
        //Query subtract = new Query("%s subtract %s", STRUCT_PAIRS); // TODO: figure out syntax
        // TODO in
        // TODO contains
        // TODO intersect
        // TODO unique eg. unique(1,2,2,4) -> 1 2 2 4
        // TODO uniqueref
        // TODO distinct
        // TODO exists
        
        // TODO projection (dot)
        // TODO continue
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(DIR + "all.txt"));
        for(Entry<String,Query> e : queries.entrySet()) {
            String name = e.getKey();
            String fileName = name + ".txt";
            
            QueryWriter qw = new QueryWriter(new FileWriter(DIR + fileName), 10, name);
            qw.write(e.getValue());
            qw.close();
            
            bw.write("batch " + fileName);
            bw.newLine();
        }
        bw.close();
    }
    
    private static final void p(String key, Query value) {
        queries.put(key, value);
    }
}
