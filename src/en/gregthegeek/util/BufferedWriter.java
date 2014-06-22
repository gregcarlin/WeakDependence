package en.gregthegeek.util;

import java.io.IOException;
import java.io.Writer;

public class BufferedWriter extends java.io.BufferedWriter {

    public BufferedWriter(Writer out) {
        super(out);
    }
    
    public BufferedWriter(Writer out, int size) {
        super(out, size);
    }

    public void writeln(String line) throws IOException {
        super.write(line);
        super.newLine();
    }
}
