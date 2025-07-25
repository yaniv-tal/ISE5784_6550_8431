package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * the class implements a Triangle.
 * @author Yaniv and Ahuvya.
 */
public class Triangle extends Polygon {
    /**
     * constructor. Gets an array of vertices,
     * calls the polygon constructor
     * and creates the triangle.
     * @param vertices
     */
    public Triangle(Point... vertices) {
        super(vertices);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        List<GeoPoint> intersection = plane.findGeoIntersections(ray, maxDistance);
        //If there is no intersection point with the plane of the triangle
        if (intersection == null)
            return null;

        //check that intersection point is closer to ray origin than
        // max distance parameter
        double distance = intersection.getFirst().point.distance(ray.getHead());
        if (alignZero(distance - maxDistance) > 0)
            return null;

        Point head = ray.getHead();
        //Checks if the intersection point is inside the triangle.
        final Vector v1 = vertices.get(0).subtract(head);
        final Vector v2 = vertices.get(1).subtract(head);
        final Vector v3 = vertices.get(2).subtract(head);
        final Vector normal1 = v1.crossProduct(v2).normalize();
        final Vector normal2 = v2.crossProduct(v3).normalize();
        final Vector normal3 = v3.crossProduct(v1).normalize();
        Vector direction = ray.getDirection();
        final double sign1 = normal1.dotProduct(direction);
        final double sign2 = normal2.dotProduct(direction);
        final double sign3 = normal3.dotProduct(direction);
        // The ray intersects the triangle. returns the intersection point.
        if (((sign1 > 0) && (sign2 > 0) && (sign3 > 0)) || ((sign1 < 0) && (sign2 < 0) && (sign3 < 0)))
            return List.of(new GeoPoint(this, intersection.getFirst().point));
        return null;
    }
}
