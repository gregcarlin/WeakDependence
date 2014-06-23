package en.gregthegeek.util;

import java.util.Calendar;

public class Random extends java.util.Random {
    private static final long serialVersionUID = -5474483383255626722L;

    public Random() {
        super();
    }
    
    public Random(long seed) {
        super(seed);
    }
    
    public <T> T choose(T... array) {
        return array[nextInt(array.length)];
    }
    
    public String nextSBQLDate() {
        int year = nextInt(40) + 1970;
        int day = nextInt(365) + 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.DAY_OF_YEAR, day);
        return String.format("%d-%02d-%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
    }
    
    public int nextInt(int min, int max) {
        return nextInt(max - min) + min;
    }
    
    public float nextFloat(float max) {
        return nextFloat() * max;
    }
    
    public float nextFloat(float min, float max) {
        return nextFloat(max - min) + min;
    }
    
    public double nextDouble(double max) {
        return nextDouble() * max;
    }
    
    public double nextDouble(double min, double max) {
        return nextDouble(max - min) + min;
    }
}
