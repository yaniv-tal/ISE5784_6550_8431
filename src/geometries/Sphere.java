package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * the class implements a sphere.
 * @author Yaniv and Ahuvya.
 */
public class Sphere extends RadialGeometry{
    private final Point center;

    /**
     * constructor. Gets a point and a radius, and creates the sphere.
     * @param radius
     * @param center
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
