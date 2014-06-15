package en.gregthegeek.gen.query;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class QueryWriter extends BufferedWriter {

    public QueryWriter(Writer out) {
        super(out);
    }

    public QueryWriter(Writer out, int sz) {
        super(out, sz);
    }
    
    public void write(Query q) throws IOException {
        for(String line : q.getLines()) {
            write(line);
            newLine();
        }
    }
}
