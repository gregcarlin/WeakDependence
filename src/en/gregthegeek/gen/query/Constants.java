package en.gregthegeek.gen.query;

public class Constants {
    protected static final String TRUE  = "true";
    protected static final String FALSE = "false";
    
    protected static final Object[][] NULL         = new Object[   1][0];
    protected static final Object[][] NUM_PAIRS    = new Object[2048][2];
    protected static final Object[][] NUM_SINGS    = new Object[2048][1];
    protected static final Object[][] NUM_SINGS_P  = new Object[1024][1];
    protected static final Object[][] LOGIC_PAIRS  = new Object[   4][2];
    protected static final Object[][] LOGIC_SINGS  = new Object[   2][1];
    protected static final Object[][] STR_PAIRS    = new Object[2048][2];
    protected static final Object[][] STR_SINGS    = new Object[2048][1];
    protected static final Object[][] DATE_OPTS    = new Object[   4][1];
    protected static final Object[][] STRUCT_SINGS = new Object[2048][1];
    protected static final Object[][] STRUCT_PAIRS = new Object[2048][2];
    
    protected static final Object[][] EMP_STRUCT_SINGS  = new Object[1024][1];
    protected static final Object[][] PART_STRUCT_SINGS = new Object[1024][1];
    
    private   static final String     LONG_STRING; // used in regex cases
    protected static final Object[][] STR_REGEX_SING = new Object[1024][2]; // uses _
    protected static final Object[][] STR_REGEX_MULT = new Object[1024][2]; // uses %
    
    private   static final String[]   WHERE_CONDS = {"hire_date > 1980-01-01", "sal > 500", "position = \"tester\"", "sex = \"female\"", "lname = \"Brooks\"", "fName = \"Ava\""};
    protected static final Object[][] WHERE_ARGS  = new Object[WHERE_CONDS.length][1];
    
    static {
        for(int i=0; i<NUM_PAIRS.length; i++) {
            NUM_PAIRS[i][0] = 63;
            NUM_PAIRS[i][1] = i - 1024;
        }
        
        for(int i=0; i<NUM_SINGS.length; i++) {
            NUM_SINGS[i][0] = i - 1024;
        }
        
        for(int i=0; i<NUM_SINGS_P.length; i++) {
            NUM_SINGS_P[i][0] = i;
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
            sb.append(getChar(i));
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
        
        StringBuilder sbEmpSings = new StringBuilder();
        for(int i=0; i<EMP_STRUCT_SINGS.length; i++) {
            sbEmpSings.append("Emp[").append(i).append("], ");
            EMP_STRUCT_SINGS[i] = new Object[] {sbEmpSings.substring(0, sbEmpSings.length() - 2)};
        }
        
        StringBuilder sbPartSings = new StringBuilder();
        for(int i=0; i<PART_STRUCT_SINGS.length; i++) {
            sbPartSings.append("Part[").append(i).append("], ");
            PART_STRUCT_SINGS[i] = new Object[] {sbPartSings.substring(0, sbPartSings.length() - 2)};
        }
        
        StringBuilder sbRegexStr = new StringBuilder("\"");
        for(int i=0; i<128; i++) { // 128 is arbitrary
            sbRegexStr.append(getChar(i));
        }
        sbRegexStr.append('"');
        LONG_STRING = sbRegexStr.toString();
        
        StringBuilder sbRegexSing = new StringBuilder("\"");
        for(int i=0; i<STR_REGEX_SING.length; i++) {
            sbRegexSing.append(i % 2 == 0 ? getChar(i) : '_');
            STR_REGEX_SING[i][0] = LONG_STRING;
            STR_REGEX_SING[i][1] = sbRegexSing.toString() + "\"";
        }
        
        StringBuilder sbRegexMult = new StringBuilder("\"");
        for(int i=0; i<STR_REGEX_MULT.length; i++) {
            sbRegexMult.append(i % 3 == 0 ? '%' : getChar(i));
            STR_REGEX_MULT[i][0] = LONG_STRING;
            STR_REGEX_MULT[i][1] = sbRegexMult.toString() + "\"";
        }
        
        StringBuilder sbWhere = new StringBuilder();
        for(int i=0; i<WHERE_ARGS.length; i++) {
            sbWhere.append(WHERE_CONDS[i]).append(" and ");
            WHERE_ARGS[i][0] = sbWhere.substring(0, sbWhere.length() - 5);
        }
    }
    
    /**
     * Converts a 0-based index to a lowercase character
     * 
     * @param i The index of the character. a = 0; z = 25
     * @return The char corresponding with the index.
     */
    public static char getChar(int i) {
        return (char) ((i % 26) + 97);
    }
}
