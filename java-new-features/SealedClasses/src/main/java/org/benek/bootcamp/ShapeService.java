package org.benek.bootcamp;

import org.benek.bootcamp.shapes.*;

public class ShapeService {
    public void printShapeDetails(Shape shape) {
        String details = switch(shape) {
            case Circle c -> "Found a circle with area " + c.area();
            case FilledRectangle f -> "Found a filled rectangle with area " + f.area();
            case TransparentRectangle t -> "Found a transparent rectangle with area " + t.area();
            case Square s -> "Found a square with area " + s.area();
            case WeirdShape w -> "Found a weird shape with area " + w.area();
        };
        System.out.println(details);
    }
}
