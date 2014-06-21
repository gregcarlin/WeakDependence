package en.gregthegeek.gen.query;

public class Constants {
    protected static final String TRUE  = "true";
    protected static final String FALSE = "false";
    
    protected static final Object[][] NULL         = new Object[   1][0];
    protected static final Object[][] NUM_PAIRS    = new Object[2048][2];
    protected static final Object[][] NUM_SINGS    = new Object[2048][1];
    protected static final Object[][] LOGIC_PAIRS  = new Object[   4][2];
    protected static final Object[][] LOGIC_SINGS  = new Object[   2][1];
    protected static final Object[][] STR_PAIRS    = new Object[2048][2];
    protected static final Object[][] STR_SINGS    = new Object[2048][1];
    protected static final Object[][] DATE_OPTS    = new Object[   4][1];
    protected static final Object[][] STRUCT_SINGS = new Object[2048][1];
    protected static final Object[][] STRUCT_PAIRS = new Object[2048][2];
    
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
        
        StringBuilder sb = new StringBuilder("\"");
        for(int i=0; i<STR_PAIRS.length; i++) {
            STR_PAIRS[i][0] = "\"asdf\"";
            sb.append((char) (((i + 1) % 26) + 97));
            STR_PAIRS[i][1] = sb.toString() + "\"";
        }
        
        DATE_OPTS[0][0] = "\"low\"";
        DATE_OPTS[1][0] = "\"medium\"";
        DATE_OPTS[2][0] = "\"high\"";
        DATE_OPTS[3][0] = "\"full\"";
        
        StringBuilder sbBags = new StringBuilder();
        for(int i=0; i<STRUCT_SINGS.length; i++) {
            sbBags.append(i).append(", ");
            STRUCT_SINGS[i] = new Object[] {sbBags.substring(0, sbBags.length() - 2)};
        }
        
        StringBuilder sbStructPairs = new StringBuilder();
        for(int i=0; i<STRUCT_PAIRS.length; i++) {
            STRUCT_PAIRS[i][0] = "1,2,3";
            sbStructPairs.append(i).append(", ");
            STRUCT_PAIRS[i][1] = sbStructPairs.substring(0, sbStructPairs.length() - 2);
        }
    }
}
