package Home6;

import Home6.Work3.Greeter;
import Home6.Work3.GreetingCasual;
import Home6.work1.Rectangle;
import Home6.work1.Square;

public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

        // I.work1
        Square square = new Square();
        square.setSide(7);
        System.out.println("Сторона квадрата: " + square.getSide());

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(9);
        rectangle.setWidth(5);
        System.out.println("Стороны прямоугольника: " + rectangle.getHeight() + " и " + rectangle.getWidth());

        // III.work3
        Greeter greeter = new Greeter();
        System.out.println(greeter.greet());
        greeter.setGreeting(new GreetingCasual());
        System.out.println(greeter.greet());

    }
}