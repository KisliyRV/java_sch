package stqa.sandbox;

public class MyFirst {

    public static void main(String[] args){
        hello("world");
        hello("user");
        hello("Roman");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника с сторонами " + r.a + " и " + r.b + " = " + r.area());


        Point p1 = new Point(2, 9);
        Point p2 = new Point(8, 5);

        System.out.println(p1.distance(p2));

        System.out.println(distance(p1, p2));

    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static double distance(Point p1, Point p2){

        double a = Math.sqrt( Math.pow((p2.x - p1.x), 2)+Math.pow((p2.y - p1.y), 2));
        return a;
    }
}

