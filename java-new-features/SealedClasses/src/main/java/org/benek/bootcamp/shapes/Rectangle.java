package org.benek.bootcamp.shapes;

public abstract sealed class Rectangle implements Shape
        permits FilledRectangle, TransparentRectangle {
}
