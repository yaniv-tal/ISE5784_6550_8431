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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point head = ray.getHead();
        Vector direction = ray.getDirection();
        //In case that the head of the ray is the center of the sphere
        if (center.equals(head))
            return List.of(new GeoPoint(this, head.add(direction.scale(radius))));
        Vector u = center.subtract(head);
        double tm = alignZero(direction.dotProduct(u));
        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm));
        //If there is no intersection point
        if (d >= radius) {
            return null;
        }
        double th = alignZero(Math.sqrt(radius * radius - d * d));
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);

        // check that distance from ray origin to intersection points
        // is smaller than max distance parameter
        boolean distanceT1 = alignZero(t1 - maxDistance) <= 0;
        boolean distanceT2 = alignZero(t2 - maxDistance) <= 0;

        //If there are two intersection points:
        if (t1 > 0 && t2 > 0 && distanceT2 && distanceT1) {
            return List.of(new GeoPoint(this, ray.getPoint(t2)), new GeoPoint(this, ray.getPoint(t1)));
        }
        //If there is one intersection point:
        if (t2 > 0 && distanceT2)
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        if (t1 > 0 && distanceT1)
            return List.of(new GeoPoint(this, ray.getPoint(t1)));

        //If there are no intersection points:
        return null;
    }
}
