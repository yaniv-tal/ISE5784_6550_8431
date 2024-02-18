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
     * calculate the direction of the lighting.
     * @param p - point
     * @return the direction of the lighting.
     */
    public Vector getL(Point p);

    /**
     * Calculation of the distance of the point from the light source
     * @param point
     * @return The distance of the point from the light source
     */
    double getDistance(Point point);

}
