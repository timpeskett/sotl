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

    public Tuple firstInc() {
        return new Tuple(x + 1, y);
    }

    public Tuple firstDec() {
        return new Tuple(x - 1, y);
    }

    public Tuple lastInc() {
        return new Tuple(x, y + 1);
    }

    public Tuple lastDec() {
        return new Tuple(x, y - 1);
    }

    public void setFirst(int x) {
        this.x = x;
    }

    public void setLast(int y) {
        this.y = y;
    }

    public int manhattan(Tuple t) {
        return Math.abs(t.first() - first()) + Math.abs(t.last() - last());
    }

    public float distance(Tuple t) {
        return (float)Math.sqrt(Math.pow(t.first() - first(), 2) + (float)Math.pow(t.last() + last(), 2));
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

    public String toString() {
        return "Tuple object { x: " + x + " y: " + y + "}";
    }
}
