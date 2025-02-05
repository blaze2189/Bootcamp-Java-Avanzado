package org.benek.bootcamp.shapes;

public non-sealed class WeirdShape implements Shape {
    private final int numberOfSides;
    private final double sideLength;

    public WeirdShape(int numberOfSides, double sideLength) {
        this.numberOfSides = numberOfSides;
        this.sideLength = sideLength;
    }

    @Override
    public double area() {
        // TODO implementar area de X lados
        return 0;
    }

    @Override
    public String describe() {
        return "Weird shape with " + numberOfSides + " sides of " + sideLength + " length each";
    }
}
