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
        Point head = ray.getHead();
        final Vector pa = head.subtract(vertices.get(0));
        final Vector pb = head.subtract(vertices.get(1));
        final Vector pc = head.subtract(vertices.get(2));
        final Vector na = pa.crossProduct(pb).normalize();
        final Vector nb = pa.crossProduct(pc).normalize();
        final Vector nc = pb.crossProduct(pc).normalize();

        if (!(na.equals(nb)) || !(nc.equals(na)) || !(nc.equals(nb)))
        {
            return null;
        }
        return plane.findIntersections(ray);
    }
}
