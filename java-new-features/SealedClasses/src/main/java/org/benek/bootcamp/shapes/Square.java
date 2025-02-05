package org.benek.bootcamp.shapes;

public final class Square implements Shape {
    private final double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double area() {
        return side * side;
    }

    @Override
    public String describe() {
        return "Square with side length: " + side;
    }
}
