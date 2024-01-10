package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * the class implements a plane.
 * @author Yaniv and Ahuvya.
 */
public class Plane implements Geometry{
    private final Point q;
    private final Vector normal;

    /**
     * constructor. Gets a point and a vector,
     * normalizes the vector and creates the surface.
     * @param q
     * @param normal
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * constructor. Gets 3 point, and creates the surface.
     * @param x
     * @param y
     * @param z
     */
    public Plane(Point x, Point y, Point z){
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
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
