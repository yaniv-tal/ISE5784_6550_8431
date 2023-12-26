package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{
    private final Point q;
    private final Vector normal;

    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    public Plane(Point x,Point y, Point z){
        this.q = x;
        this.normal = null;
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    public Vector getNormal() {
        return normal;
    }
}
