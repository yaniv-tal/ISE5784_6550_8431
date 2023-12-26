package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry{
    protected final Ray aray;

    public Tube(double radius, Ray aray) {
        super(radius);
        this.aray = aray;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
