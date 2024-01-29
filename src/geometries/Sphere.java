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
     * @param radius for the radius of the sphere.
     * @param center for the center of the sphere.
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point head = ray.getHead();
        Vector direction = ray.getDirection();
        //In case that the head of the ray is the center of the sphere
        if (center.equals(head))
            return List.of(new GeoPoint(this, head.add(direction.scale(radius))));
        Vector u = center.subtract(head);
        double tm = direction.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        //If there is no intersection point
        if (d >= radius) {
            return null;
        }
        double th = Math.sqrt(radius * radius - d * d);
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);
        //If there are no intersection points:
        if (t2 <= 0 && t1 <= 0)
            return null;
        //If there is one intersection point:
        if (t2 <= 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        if (t1 <= 0)
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        //If there are two intersection points:
        return List.of(new GeoPoint(this, ray.getPoint(t2)), new GeoPoint(this, ray.getPoint(t1)));
    }
}
