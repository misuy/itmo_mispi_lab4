package com.example.lab3.entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class Dot implements Serializable {
    private double x = 0;
    private double y = 0;
    private double r = 1;

    public Dot() {}

    public Dot(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getX() { return this.x; }

    public double getY() { return this.y; }

    public double getR() { return this.r; }
}
