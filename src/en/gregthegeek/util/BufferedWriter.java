package en.gregthegeek.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class BufferedWriter extends java.io.BufferedWriter {
    
    public BufferedWriter(String file) throws IOException {
        this(new FileWriter(file));
    }
    
    public BufferedWriter(File file) throws IOException {
        this(new FileWriter(file));
    }

    public BufferedWriter(Writer out) {
        super(out);
    }
    
    public BufferedWriter(String file, int size) throws IOException {
        this(new FileWriter(file), size);
    }
    
    public BufferedWriter(File file, int size) throws IOException {
        this(new FileWriter(file), size);
    }
    
    public BufferedWriter(Writer out, int size) {
        super(out, size);
    }

    public void writeln(String line) throws IOException {
        write(line);
        newLine();
    }
    
    public void writef(String format, Object... args) throws IOException {
        write(String.format(format, args));
    }
    
    public void writelnf(String format, Object... args) throws IOException {
        writeln(String.format(format, args));
    }
}
