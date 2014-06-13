package en.gregthegeek.gen;

import 

public class QueryGen {
    private static final String TRUE  = "\"true\"";
    private static final String FALSE = "\"false\"";
    
    private static final Object[][] NULL        = new Object[   0][0];
    private static final Object[][] NUM_PAIRS   = new Object[2048][2];
    private static final Object[][] NUM_SINGS   = new Object[2048][1];
    private static final Object[][] LOGIC_PAIRS = new Object[   4][2];
    private static final Object[][] LOGIC_SINGS = new Object[   2][1];
    private static final Object[][] STR_PAIRS   = new Object[2048][2];
    private static final Object[][] STR_SINGS   = new Object[2048][1];
    private static final Object[][] DATE_OPTS   = new Object[   4][1];
    private static final Object[][] BAGS        = new Object[2048][1];
    
    static {
        for(int i=0; i<NUM_PAIRS.length; i++) {
            NUM_PAIRS[i][0] = 63;
            NUM_PAIRS[i][1] = i - 1024;
        }
        
        for(int i=0; i<NUM_SINGS.length; i++) {
            NUM_SINGS[i][0] = i - 1024;
        }
        
        LOGIC_PAIRS[0][0] = TRUE;
        LOGIC_PAIRS[0][1] = TRUE;
        
        LOGIC_PAIRS[1][0] = TRUE;
        LOGIC_PAIRS[1][1] = FALSE;
        
        LOGIC_PAIRS[2][0] = FALSE;
        LOGIC_PAIRS[2][1] = TRUE;
        
        LOGIC_PAIRS[3][0] = FALSE;
        LOGIC_PAIRS[3][1] = TRUE;
        
        LOGIC_SINGS[0][0] = TRUE;
        LOGIC_SINGS[1][0] = FALSE;
        
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<STR_PAIRS.length; i++) {
            STR_PAIRS[i][0] = "asdf";
            sb.append((char) (((i + 1) % 26) + 97));
            STR_PAIRS[i][1] = sb.toString();
        }
        
        DATE_OPTS[0][0] = "low";
        DATE_OPTS[1][0] = "medium";
        DATE_OPTS[2][0] = "high";
        DATE_OPTS[3][0] = "full";
        
        StringBuilder sbBags = new StringBuilder();
        for(int i=0; i<BAGS.length; i++) {
            sbBags.append(i).append(", ");
            BAGS[i] = sbBags.substring(0, sbBags.length() - 2);
        }
    }
    
    public static void main(String[] args) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/Users/greg/Documents/School/Science_Research/workspace/TODO"))); // TODO
        
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
        
        Query sum = new Query("sum %s"  , BAGS);
        Query cnt = new Query("count %s", BAGS);
        Query min = new Query("min %s"  , BAGS);
        Query max = new Query("max %s"  , BAGS);
        Query avg = new Query("avg %s"  , BAGS);
        
        bw.close();
    }
    
    private static class Query() {
        private final String string;
        private final Object[][] args;
        
        public Query(String string) {
            this(string, NULL);
        }
        
        public Query(String string, Object[][] args) {
            this.string = string + ";";
            this.args = args;
        }
    }
}
