package net.thirteen.sotl.utils;


public class Tuple {
    private int x, y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int first() {
        return x;
    }

    public int last() {
        return y;
    }
}
