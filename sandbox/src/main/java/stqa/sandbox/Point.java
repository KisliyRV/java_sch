package stqa.sandbox;

public class Point {

    public int p1;
    public int p2;


    public Point(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double distance() {
        double x1 = 5;
        double y1 = 4;
        double x2 = 6;
        double y2 = 7;

        double p1 = Math.abs(y2 - y1);
        double p2 = Math.abs(x2 - x1);
        return Math.hypot(p1, p2);
    }
}
