package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    public List<Point> findIntersections(Ray ray) {
        final Vector pa = ray.getHead().subtract(vertices.get(1));
        final Vector pb = ray.getHead().subtract(vertices.get(2));
        final Vector pc = ray.getHead().subtract(vertices.get(3));

        return null;
    }
}
