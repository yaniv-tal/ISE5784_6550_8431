package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * A class for representing directional light.
 * @author Yaniv and Ahuvya.
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * constructor
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public List<Vector> getLBeam(Point p) {
        return List.of(getL(p));
    }

}
