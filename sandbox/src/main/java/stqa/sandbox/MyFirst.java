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


        Point p = new Point(2.0, 5.0);
        System.out.println(p.p1 + " + " + p.p2 + " = " + p.distance());

    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}

