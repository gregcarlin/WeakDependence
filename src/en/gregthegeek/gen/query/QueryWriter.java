package en.gregthegeek.gen.query;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class QueryWriter extends BufferedWriter {
    private final int tests;
    private final String prefix;
    
    public QueryWriter(String out, int tests, String prefix) throws IOException {
        this(new File(out), tests, prefix);
    }
    
    public QueryWriter(String out, int tests, String prefix, int sz) throws IOException {
        this(new File(out), tests, prefix, sz);
    }
    
    public QueryWriter(File out, int tests, String prefix) throws IOException {
        this(new FileWriter(out), tests, prefix);
    }
    
    public QueryWriter(File out, int tests, String prefix, int sz) throws IOException {
        this(new FileWriter(out), tests, prefix, sz);
    }

    public QueryWriter(Writer out, int tests, String prefix) throws IOException {
        super(out);
        this.tests = tests;
        this.prefix = prefix;
        init();
    }

    public QueryWriter(Writer out, int tests, String prefix, int sz) throws IOException {
        super(out, sz);
        this.tests = tests;
        this.prefix = prefix;
        init();
    }
    
    private final void init() throws IOException {
        writeln("$encoding = utf-8");
        newLine();
    }
    
    public void writeln(String line) throws IOException {
        write(line);
        newLine();
    }
    
    public void write(Query q) throws IOException {
        for(String line : q.getLines()) {
            writeln("benchmark " + tests + " " + prefix + ":" + line);
        }
    }
}
