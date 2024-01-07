package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * interface of geometric shapes.
 * @author Yaniv and Ahuvya.
 */
public interface Geometry extends Intersectable{
    /**
     * @param point
     * @return The normal (vertical) vector to the body at this point.
     */
    public Vector getNormal(Point point);
}

