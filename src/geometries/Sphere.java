package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * the class implements a sphere.
 * @author Yaniv and Ahuvya.
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * constructor. Gets a point and a radius, and creates the sphere.
     *
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
        Point head = ray.getHead();
        Vector direction = ray.getDirection();
        if (center.equals(head))
            return List.of(head.add(direction.scale(radius)));
        Vector u = center.subtract(head);
        double tm = direction.dotProduct(u);
        double d = Math.sqrt(u.length() * u.length() - tm * tm);
        if (d >= radius) {
            return null;
        }

        double th = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(th + tm);
        double t2 = alignZero(th - tm);
        if (t2<=0)
            return List.of(ray.getPoint(t1));
        if (t1<=0)
            return List.of(ray.getPoint(t2));
        return List.of(ray.getPoint(t2), ray.getPoint(t1));
    }
}
