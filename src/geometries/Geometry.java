package geometries;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface of geometric shapes.
 * @author Yaniv and Ahuvya.
 */
public abstract class Geometry extends Intersectable{
    protected Color emission = Color.BLACK;

    /**
     * getter function for the field emission.
     * @return emission
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * setter function for the field emission.
     *
     * @param emission presents the emission to set.
     * @return
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * @param point
     * @return The normal (vertical) vector to the body at this point.
     */
    public abstract Vector getNormal(Point point);
}

