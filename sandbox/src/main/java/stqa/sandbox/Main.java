package stqa.sandbox;

public class Main {

    public static void main(String[] args){

        PointTwo p = new PointTwo(2.0, 5.0);
        System.out.println(p.p1 + " + " + p.p2 + " = " + p.distanceTwo());

    }

    public static double distanceTwo(Point p1, Point p2) {
        return Math.sqrt(p1.p2 - p1.p1) + Math.sqrt(p2.p2 - p1.p1);
    }
}
