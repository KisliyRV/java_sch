package stqa.sandbox;

public class Point {

    public double p1;
    public double p2;


    public Point(double p1, double p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double distance() {
        double x1 = 4;
        double y1 = 8;
        double x2 = 6;
        double y2 = 5;

        double p1 = Math.abs(y2 - y1);
        double p2 = Math.abs(x2 - x1);
        return Math.hypot(p1, p2);
       // return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
}
