package com.example.lab3.model;

import com.example.lab3.entities.Attempt;
import com.example.lab3.entities.Dot;

public class DotProcessor {
    public static boolean processDot(Dot dot) {
        if (dot.getX() >= 0) {
            if (dot.getY() >= 0) {
                return Math.sqrt(Math.pow(dot.getX(), 2) + Math.pow(dot.getY(), 2)) <= dot.getR()/2;
            }
            else return dot.getY() >= (dot.getX() - dot.getR()/2);
        }
        else if (dot.getY() <= 0) return (dot.getX() >= -dot.getR()) && (dot.getY() >= -dot.getR()/2);
        return false;
    }
}
