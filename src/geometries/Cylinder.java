package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

/**
 * the class implements a cylinder.
 * @author Yaniv and Ahuvya.
 */
public class Cylinder extends Tube{
    private final double height;

    /**
     *constructor. Gets a radius, and a ray to the direction,
     * and height of the cylinder.
     * Creates a pipe by calling its constructor, and creates the cylinder.
     * @param radius
     * @param aray
     * @param height
     */
    public Cylinder(double radius, Ray aray, double height) {
        super(radius, aray);
        this.height = height;
    }

    @Override
    public Vector getNormal(Point point) {
        // Get the starting point(head) and the direction vector
        Point p0 = aray.getHead();
        Vector v = aray.getDirection();

        //Get the central of the top base.
        Point o2 = p0.add(v.scale(height));

        //Check if the given point is in the central of the top or the bottom of the cylinder.
        if (p0.equals(point) || o2.equals(point))
            return v;

        //check if the point is in the bottom base.
        double t = v.dotProduct(point.subtract(p0));
        if (isZero(t))
            return v;

        //check if the point is in the top base.
        double t2 = v.dotProduct(point.subtract(o2));
        if (isZero(t2))
            return v;

        // Return the normal of the specified point on the surface, normalized.
        return point.subtract(aray.getPoint(t)).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return super.findGeoIntersections(ray,maxDistance);
    }
    @Override
    public List<Point> findBVHPoints() {
        return null;
    }
}
