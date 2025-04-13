package org.example.model;


public class Coordinates {
    private Double x; //Поле не может быть null
    private double y;

    public Coordinates(Double x, double y) {
        if (x == null) {
            throw new IllegalArgumentException("X coordinate cannot be null");
        }
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
} 