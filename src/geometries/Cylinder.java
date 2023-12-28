package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        return null;
    }
}
