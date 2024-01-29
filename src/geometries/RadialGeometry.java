package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * the class implements a geometric shape with radius.
 * @author Yaniv and Ahuvya.
 */

public abstract class RadialGeometry extends Geometry{
    protected final double radius;

    /**
     *constructor.
     * @param radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

}
