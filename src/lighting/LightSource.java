package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * interface of light source.
 * @author Yaniv and Ahuvya.
 */
public interface LightSource {
    /**
     * function for get the light intensity in a point.
     * @param p represents the getting point.
     * @return the light intensity in the point.
     */
    public Color getIntensity(Point p);

    /**
     *
     * @param p
     * @return
     */
    public Vector getL(Point p);
}
