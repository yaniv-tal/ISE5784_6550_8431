package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * the class implements a Tube.
 * @author Yaniv and Ahuvya.
 */
public class Tube extends RadialGeometry{
    protected final Ray aray;

    /**
     * constructor. Gets a radius, and a ray that symbolizes the tube, and creates it.
     * @param radius
     * @param aray
     */
    public Tube(double radius, Ray aray) {
        super(radius);
        this.aray = aray;
    }

    @Override
    public Vector getNormal(Point point) {
        double t = aray.getDirection().dotProduct(point.subtract(aray.getHead()));
        //A bound case where the point is in front of the ray head.
        if(t==0) { return point.subtract(aray.getHead());}

        //Calculate and return the normal vector fot this point on the tube.
        return point.subtract(aray.getHead().add(aray.getDirection().scale(t))).normalize();
    }
}
