package org.benek.bootcamp;

import org.benek.bootcamp.shapes.*;

public class Main {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape filledRectangle = new FilledRectangle(20, 10);
        Shape transparentRectangle = new TransparentRectangle(30, 10);
        Shape square = new Square(4);
        Shape weird = new WeirdShape(8, 10);

        ShapeService service = new ShapeService();
        service.printShapeDetails(circle);
        service.printShapeDetails(filledRectangle);
        service.printShapeDetails(transparentRectangle);
        service.printShapeDetails(square);
        service.printShapeDetails(weird);
    }
}
