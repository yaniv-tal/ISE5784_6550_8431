package geometries;

import primitives.Point;
import primitives.Vector;

public interface Geometry {
    /**
     * @param point
     * @return The normal (vertical) vector to the body at this point.
     */
    public Vector getNormal(Point point);
}
