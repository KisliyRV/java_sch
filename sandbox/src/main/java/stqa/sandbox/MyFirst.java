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


        Point p = new Point(5, 2);
        System.out.println("Расстояние между точками = " + p.distance());
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

}

