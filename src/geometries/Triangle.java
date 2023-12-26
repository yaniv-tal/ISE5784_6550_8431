package geometries;

import primitives.Point;

public class Triangle extends Polygon {
    /**
     *constructor. Gets an array of vertices,
     * calls the polygon constructor
     * and creates the triangle.
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }
}
