package org.benek.bootcamp.shapes;

public final class TransparentRectangle extends Rectangle {
    private final double width;
    private final double height;

    public TransparentRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String describe() {
        return "Transparent rectangle with width " + width + " and height " + height;
    }
}
