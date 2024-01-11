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
     * constructor. Gets an array of vertices,
     * calls the polygon constructor
     * and creates the triangle.
     *
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = plane.findIntersections(ray);
        if (intersections == null)
            return null;

        Point head = ray.getHead();
        final Vector p1 = vertices.get(0).subtract(head);
        final Vector p2 = vertices.get(1).subtract(head);
        final Vector p3 = vertices.get(2).subtract(head);
        final Vector n1 = p1.crossProduct(p2).normalize();
        final Vector n2 = p2.crossProduct(p3).normalize();
        final Vector n3 = p3.crossProduct(p1).normalize();
        Vector v = ray.getDirection();
        final double s1 = n1.dotProduct(v);
        final double s2 = n2.dotProduct(v);
        final double s3 = n3.dotProduct(v);
        if ((s1 == 0) || (s2 == 0) || (s3 == 0))
            return null;
        if (((s1 > 0) && (s2 > 0) && (s3 > 0)) || ((s1 < 0) && (s2 < 0) && (s3 < 0)))
            return intersections;
        return null;
    }
}
