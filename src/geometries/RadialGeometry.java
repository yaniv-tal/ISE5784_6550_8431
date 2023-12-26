package geometries;

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
