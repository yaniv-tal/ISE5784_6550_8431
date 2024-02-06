package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * A class for representing point light.
 * @author Yaniv and Ahuvya.
 */
public class PointLight extends Light implements LightSource {
    private Point position;
    //Factors for attenuation with distance (d)
    private double kC = 1;
    //Factors for attenuation with distance (d)
    private double kL = 0;
    //Factors for attenuation with distance (d)
    private double kQ = 0;

    protected double narrowBeam = 1;

    /**
     * constructor
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * set function for KS
     * @param kC
     * @return the object
     */
    public PointLight setKC(double kC) {
        this.kC = kC;
        return this;
    }
    /**
     * set function for kl
     * @param kL
     * @return the object
     */
    public PointLight setKL(double kL) {
        this.kL = kL;
        return this;
    }
    /**
     * set function for kQ
     * @param kQ
     * @return the object
     */
    public PointLight setKQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity.scale(1 / (kC + kL * position.distance(p) + kQ * position.distanceSquared(p)));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
    /**
     * set function for NarrowBeam
     * @param narrowBeam
     * @return the object
     */
    public PointLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
