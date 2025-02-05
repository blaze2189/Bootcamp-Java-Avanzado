package org.benek.bootcamp.shapes;

public final class FilledRectangle extends Rectangle {
    private final double width;
    private final double height;

    public FilledRectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String describe() {
        return "Filled rectangle with width " + width + " and height " + height;
    }
}
