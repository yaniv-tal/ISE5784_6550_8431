package lighting;

import geometries.Plane;
import primitives.Color;
import primitives.Point;
import primitives.Vector;
import renderer.Blackboard;

import java.util.LinkedList;
import java.util.List;
import static primitives.Util.random;

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

    @Override
    public List<Vector> getLBeam(Point p,Blackboard blackboardSoftShadows) {
        if (!blackboardSoftShadows.rayBeam() ) return List.of(getL(p));
        List<Vector> vectors = new LinkedList<>();
        Vector vTo = getL(p);
        Vector vUp = new Vector(vTo.getZ(), 0 ,-vTo.getX()).normalize();
        Vector vRight = vTo.crossProduct(vUp).normalize();
        blackboardSoftShadows.setGrid(position,vUp,vRight);
        for (Point i : blackboardSoftShadows.grid)
            vectors.add(p.subtract(i).normalize());
        return vectors;
    }
}

