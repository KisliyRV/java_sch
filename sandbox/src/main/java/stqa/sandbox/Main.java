package stqa.sandbox;

public class Main {

    public static void main(String[] args){

        PointTwo p1 = new PointTwo(2, 5);
        PointTwo p2 = new PointTwo(4.0, 8.0);
        System.out.println(p1.p1 + " + " + p2.p2 + " = " + distanceTwo(p1, p2));

    }

    public static double distanceTwo(PointTwo p1, PointTwo p2) {
        return Math.sqrt(p1.p2 - p1.p1) + Math.sqrt(p2.p2 - p2.p1);
    }
    
}
