package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * the class implements a plane.
 * @author Yaniv and Ahuvya.
 */
public class Plane extends Geometry {
    private final Point q;
    private final Vector normal;

    /**
     * constructor. Gets a point and a vector,
     * normalizes the vector and creates the surface.
     *
     * @param q
     * @param normal
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * constructor. Gets 3 point, and creates the surface.
     *
     * @param x
     * @param y
     * @param z
     */
    public Plane(Point x, Point y, Point z) {
        this.q = x;
        this.normal = z.subtract(y).crossProduct(z.subtract(x)).normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * The function return the normal of the plane.
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point head = ray.getHead();
        Vector direction = ray.getDirection();

        // Check for zero vector.
        if (head.equals(q))
            return null;

        // Check that the denominator is not zero.
        double nv = normal.dotProduct(direction);
        if (isZero(nv))
            return null;

        double t = alignZero(normal.dotProduct(q.subtract(head)) / nv);

        //check that the calculate is positive.
        if (t <= 0)
            return null;
        return List.of(new GeoPoint(this,ray.getPoint(t)));
    }
}
