package org.benek.bootcamp.shapes;

public sealed interface Shape permits Circle, Rectangle, Square, WeirdShape {
    double area();
    String describe();
}
