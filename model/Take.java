package model;

public class Take {

    private int takeNum;
    private int x;
    private int y;
    private int h;
    private int w;

    public Take(int takeNum, int x, int y, int h, int w) {
        this.takeNum = takeNum;
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public int getTakeNum() {
        return takeNum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }
}
