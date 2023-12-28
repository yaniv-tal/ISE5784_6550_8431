package geometries;

/**
 * the class implements a geometric shape with radius.
 * @author Yaniv and Ahuvya.
 */

public abstract class RadialGeometry implements Geometry{
    protected final double radius;

    /**
     *constructor.
     * @param radius
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
