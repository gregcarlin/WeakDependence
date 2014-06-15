package en.gregthegeek.gen.query;

public class Query {
    private final String string;
    private final Object[][] args;
    
    public Query(String string) {
        this(string, Constants.NULL);
    }
    
    public Query(String string, Object[][] args) {
        this.string = string + ";";
        this.args = args;
    }
    
    public String[] getLines() {
        String[] lines = new String[args.length];
        for(int i=0; i<lines.length; i++) {
            lines[i] = String.format(string, args[i]);
        }
        return lines;
    }
}
