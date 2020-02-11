package stqa.sandbox;

public class PointTwo {

    public double x1;
    public double x2;
    public double y1;
    public double y2;
    public double p1;
    public double p2;

    public PointTwo(double p1, double p2) {
        this.x1 = p1;
        this.x2 = p1;
        this.y1 = p2;
        this.y2 = p2;
    }

    public double distanceTwo(Point p2) {
        p1.distanceTwo(p2);
        return Math.sqrt(p1.p2 - p1.p1) + Math.sqrt(p2.p2 - p2.p1);
    }

}
