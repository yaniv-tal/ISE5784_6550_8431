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
        List<Point> r = plane.findIntersections(ray);
        if (r==null)
            return null;
        final Vector pa = r.get(1).subtract(vertices.get(0));
        final Vector pb = r.get(1).subtract(vertices.get(1));
        final Vector pc = r.get(1).subtract(vertices.get(2));
        final Vector na = pa.crossProduct(pb).normalize();
        final Vector nb = pa.crossProduct(pc).normalize();
        final Vector nc = pb.crossProduct(pc).normalize();

        if (!(na.equals(nb)) || !(nc.equals(na)) || !(nc.equals(nb)))
        {
            return null;
        }
    }
}
