package geometries;

import primitives.Point;
import primitives.Vector;

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
        return null;
    }
}
