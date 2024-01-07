package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * the class implements a Triangle.
 * @author Yaniv and Ahuvya.
 */
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

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
