package en.gregthegeek.gen;

import 

public class QueryGen {
    private static final String TRUE = "\"true\"";
    private static final String FALSE = "\"false\"";
    
    private static final Object[][] NUM_PAIRS   = new Object[2048][2];
    private static final Object[][] NUM_SINGS   = new Object[2048][1];
    private static final Object[][] LOGIC_PAIRS = new Object[   4][2];
    private static final Object[][] LOGIC_SINGS = new Object[   2][1];
    private static final Object[][] STR_PAIRS   = new Object[2048][2];
    private static final Object[][] STR_SINGS   = new Object[2048][1];
    
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
        
        for(int i=0; i<STR_PAIRS.length; i++) {
            STR_PAIRS[i][0] = "asdf";
            STR_PAIRS[i][1] = getString(i + 1); // TODO cache StringBuilder to improve speed
        }
    }
    
    private static String getString(int len) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<len; i++) {
            sb.append((char) ((i % 26) + 97));
        }
        return sb.toString();
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
        
        Query cat = new Query("%s + %s", STR_PAIRS);
        
        bw.close();
    }
    
    private static class Query() {
        private final String string;
        private final Object[][] args;
        
        public Query(String string, Object[][] args) {
            this.string = string + ";";
            this.args = args;
        }
    }
}
