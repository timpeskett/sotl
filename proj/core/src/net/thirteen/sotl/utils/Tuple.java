package net.thirteen.sotl.utils;


public class Tuple {
    private int x, y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tuple(Tuple t) {
        x = t.first();
        y = t.last();
    }

    public int first() {
        return x;
    }

    public int last() {
        return y;
    }

    public void setFirst(int x) {
        this.x = x;
    }

    public void setLast(int y) {
        this.y = y;
    }

    public int hashCode() {
        return first() + 100 * last();
    }

    public boolean equals(Object o) {
        if(o instanceof Tuple) {
            Tuple t = (Tuple)o;
            return t.first() == first() && t.last() == last();
        }
        return false;
    }
}
