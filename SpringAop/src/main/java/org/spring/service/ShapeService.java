package org.spring.service;

import org.spring.model.Circle;
import org.spring.model.Triangle;

/**
 * Created by igaurav on 1/22/2017.
 */
public class ShapeService {

    private Circle circle;
    private Triangle triangle;

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

    public Triangle getTriangle() {
        return triangle;
    }

    public void setTriangle(Triangle triangle) {
        this.triangle = triangle;
    }
}
